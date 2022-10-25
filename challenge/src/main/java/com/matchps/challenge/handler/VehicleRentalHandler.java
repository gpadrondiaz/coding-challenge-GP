package com.matchps.challenge.handler;

import com.matchps.challenge.constants.Constants;
import com.matchps.challenge.controller.request.CreateRentalRequest;
import com.matchps.challenge.entities.Renter;
import com.matchps.challenge.entities.Vehicle;
import com.matchps.challenge.entities.VehicleRental;
import com.matchps.challenge.gateway.Payment;
import com.matchps.challenge.gateway.Scheduler;
import com.matchps.challenge.gateway.Texter;
import com.matchps.challenge.model.NoAvailableVehicleStock;
import com.matchps.challenge.model.VehicleModel;
import com.matchps.challenge.model.VehicleRentalModel;
import com.matchps.challenge.repository.RenterRepository;
import com.matchps.challenge.repository.VehicleRentalRepository;
import com.matchps.challenge.repository.VehicleRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class VehicleRentalHandler {
    @Autowired
    private RenterRepository renterRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleRentalRepository vehicleRentalRepository;

    @Autowired
    private Texter texter;

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private Payment payment;

    public void createRental(CreateRentalRequest createRentalRequest) throws Exception {
        Vehicle vehicle = vehicleRepository.getById(createRentalRequest.getVehicleId());
        Renter renter = renterRepository.getById(createRentalRequest.getRenterId());

        if (vehicle == null || renter == null) {
            System.out.println("Renter or vehicle doesn't exist");
            throw new Exception();
        }

        if (!vehicle.getState().equalsIgnoreCase(Constants.VehicleState.AVAILABLE.toString())) {
            System.out.println("Vehicle already rented");
            throw new Exception();
        }

        VehicleRental vehicleRental =  new VehicleRental();
        vehicleRental.setRenterId(createRentalRequest.getRenterId());
        Date date = new Date();
        Timestamp createdAt = new Timestamp(date.getTime());
        vehicleRental.setCreatedAt(createdAt);
        vehicleRental.setScheduledPickUpDate(Timestamp.valueOf(createRentalRequest.getScheduledPickUpDate()));
        vehicleRental.setScheduledDropOffDate(Timestamp.valueOf(createRentalRequest.getScheduledDropOffDate()));
        vehicleRental.setState(Constants.VehicleRentalState.SCHEDULED.toString());
        vehicleRental.setRate(1000.01);
        vehicleRental.setVehicleId(createRentalRequest.getVehicleId());

        vehicleRentalRepository.save(vehicleRental);

        // change vehicle state
        vehicle.setUpdatedAt(createdAt);
        vehicle.setState(Constants.VehicleState.RESERVED.toString());
        vehicleRepository.save(vehicle);

        // send text message
        texter.sendText(renter.getTelephoneNumber(), "Your vehicle has been rented");

        // scheduled event
        scheduler.schedule(24 * 3600, new Function() {
            @Override
            public Object apply(Object o) {
                return null;
            }
        }, new String[] { createRentalRequest.getRenterId().toString(), createRentalRequest.getScheduledDropOffDate().toString(), "Pick up advice" });
    }

    public void pickUpVehicle(Long vehicleRentalId, String pickUpDate) throws Exception {
        VehicleRental vehicleRental = vehicleRentalRepository.getById(vehicleRentalId);
        if (vehicleRental == null) {
            System.out.println("Rental doesn't exist");
            throw new Exception();
        }
        vehicleRental.setState(Constants.VehicleRentalState.PICKED_UP.toString());
        Timestamp todayDate = Timestamp.valueOf(pickUpDate);
        vehicleRental.setFinalPickUpDate(todayDate);
        Timestamp updatedDate = new Timestamp((new Date()).getTime());
        vehicleRental.setUpdatedAt(updatedDate);
        vehicleRentalRepository.save(vehicleRental);

        // change vehicle state
        Vehicle vehicle = vehicleRepository.getById(vehicleRental.getVehicleId());
        vehicle.setUpdatedAt(updatedDate);
        vehicle.setState(Constants.VehicleState.RENTED.toString());
        vehicleRepository.save(vehicle);

        Renter renter = renterRepository.getById(vehicleRental.getRenterId());

        //charge payment
        payment.process(vehicleRental.getId().toString(), (float) vehicleRental.getRate(), renter.getCreditCardNumber());

        // send text message
        texter.sendText(renter.getTelephoneNumber(), "Your vehicle has been picked up");

        // scheduled event
        scheduler.schedule(0, new Function() {
            @Override
            public Object apply(Object o) {
                return null;
            }
        }, new String[] { vehicleRental.getRenterId().toString(), pickUpDate, "Vehicle picked up" });
    }

    public void dropOffVehicle(Long vehicleRentalId, String dropOffDate) throws Exception{
        VehicleRental vehicleRental = vehicleRentalRepository.getById(vehicleRentalId);
        if (vehicleRental == null) {
            System.out.println("Rental doesn't exist");
            throw new Exception();
        }

        vehicleRental.setState(Constants.VehicleRentalState.DROPPED_OFF.toString());
        Timestamp dropOffDateTS = Timestamp.valueOf(dropOffDate);
        vehicleRental.setFinalDropOffDate(dropOffDateTS);
        boolean isDropOffAfterDate = vehicleRental.getScheduledDropOffDate().before(dropOffDateTS);
        double finalRate = isDropOffAfterDate ? vehicleRental.getRate() * 1.5 : vehicleRental.getRate();
        vehicleRental.setFinalRate(finalRate);
        Timestamp updatedDate = new Timestamp((new Date()).getTime());
        vehicleRental.setUpdatedAt(updatedDate);
        vehicleRentalRepository.save(vehicleRental);

        Renter renter = renterRepository.getById(vehicleRental.getRenterId());

        // change vehicle state
        Vehicle vehicle = vehicleRepository.getById(vehicleRental.getVehicleId());
        vehicle.setUpdatedAt(updatedDate);
        vehicle.setState(Constants.VehicleState.AVAILABLE.toString());
        vehicleRepository.save(vehicle);

        // send text message
        texter.sendText(renter.getTelephoneNumber(), "Your vehicle has been dropped up");
        if (isDropOffAfterDate) {
            texter.sendText(renter.getTelephoneNumber(), "Your vehicle has been dropped up after scheduled date and was charged this amount: " + finalRate);
            //charge payment
            payment.process(vehicleRental.getId().toString(), (float) finalRate, renter.getCreditCardNumber());
        }
    }

    public void cancelRental(Long vehicleRentalId, String cancelDate) throws Exception {
        VehicleRental vehicleRental = vehicleRentalRepository.getById(vehicleRentalId);
        if (vehicleRental == null) {
            System.out.println("Rental doesn't exist");
            throw new Exception();
        }
        vehicleRental.setState(Constants.VehicleRentalState.CANCELLED.toString());
        Timestamp cancelDateTS = Timestamp.valueOf(cancelDate);
        Timestamp updatedDate = new Timestamp((new Date()).getTime());
        vehicleRental.setUpdatedAt(updatedDate);
        long timeDifference =  vehicleRental.getScheduledPickUpDate().getTime() - cancelDateTS.getTime();
        int seconds = (int) timeDifference / 1000;
        int hours = seconds / 3600;
        double finalRate = 0;
        if (hours <= 24) {
            finalRate = vehicleRental.getRate();
        } else if (hours >= 48 && hours < 24) {
            finalRate = vehicleRental.getRate() * 0.25;
        }
        vehicleRental.setFinalRate(finalRate);
        vehicleRentalRepository.save(vehicleRental);

        // change vehicle state
        Vehicle vehicle = vehicleRepository.getById(vehicleRental.getVehicleId());
        vehicle.setUpdatedAt(updatedDate);
        vehicle.setState(Constants.VehicleState.AVAILABLE.toString());
        vehicleRepository.save(vehicle);
        Renter renter = renterRepository.getById(vehicleRental.getRenterId());

        if (finalRate > 0) {
            //charge payment
            payment.process(vehicleRental.getId().toString(), (float) finalRate, renter.getCreditCardNumber());
        }

        // send text message
        texter.sendText(renter.getTelephoneNumber(), "Your vehicle rent has been cancelled with this final rate "+ finalRate);
    }

    public void updateRental(Long vehicleRentalId, String dropOffDate, String pickUpDate) throws Exception {
        VehicleRental vehicleRental = vehicleRentalRepository.getById(vehicleRentalId);
        if (vehicleRental == null) {
            System.out.println("Rental doesn't exist");
            throw new Exception();
        }

        if (Strings.isBlank(dropOffDate) && Strings.isBlank(pickUpDate)) {
            System.out.println("Invalid dates");
            throw new Exception();
        }

        vehicleRental.setState(Constants.VehicleRentalState.CANCELLED.toString());
        vehicleRental.setUpdatedAt(new Timestamp((new Date()).getTime()));
        if (dropOffDate != null) {
            vehicleRental.setScheduledDropOffDate(Timestamp.valueOf(dropOffDate));
        }
        if (pickUpDate != null) {
            vehicleRental.setScheduledPickUpDate(Timestamp.valueOf(pickUpDate));
        }
    }

    public List<NoAvailableVehicleStock> getNoAvailableVehicle() {
        List<NoAvailableVehicleStock> vehicleRentalModelList = new ArrayList<>();
        List<String> states = Arrays.asList(Constants.VehicleRentalState.SCHEDULED.toString(), Constants.VehicleRentalState.PICKED_UP.toString());

        List<VehicleRental> vehicles = vehicleRentalRepository.findByStateNotIn(states);
        vehicleRentalModelList = vehicles.stream().map((vehicleRent -> {
            NoAvailableVehicleStock vehicleModel = new NoAvailableVehicleStock();
            vehicleModel.setLicensePlate(vehicleRent.getVehicle().getLicensePlate());
            vehicleModel.setDropOffDate(vehicleRent.getScheduledDropOffDate().toString());
            return vehicleModel;
        })).collect(Collectors.toList());

        return vehicleRentalModelList;
    }
}
