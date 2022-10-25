package com.matchps.challenge.handler;

import com.matchps.challenge.controller.request.CreateRentalRequest;
import com.matchps.challenge.entities.Renter;
import com.matchps.challenge.entities.Vehicle;
import com.matchps.challenge.entities.VehicleRental;
import com.matchps.challenge.gateway.Payment;
import com.matchps.challenge.gateway.Scheduler;
import com.matchps.challenge.gateway.Texter;
import com.matchps.challenge.repository.RenterRepository;
import com.matchps.challenge.repository.VehicleRentalRepository;
import com.matchps.challenge.repository.VehicleRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.function.Function;

public class VehicleRentalHandlerTest {

    @InjectMocks
    private VehicleRentalHandler vehicleRentalHandler;

    @Mock
    private RenterRepository renterRepository;

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private VehicleRentalRepository vehicleRentalRepository;

    @Mock
    private Texter texter;

    @Mock
    private Scheduler scheduler;

    @Mock
    private Payment payment;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        Vehicle vehicle = new Vehicle();
        vehicle.setState("AVAILABLE");
        Mockito.when(vehicleRepository.getById(Mockito.anyLong())).thenReturn(vehicle);
        Mockito.when(renterRepository.getById(Mockito.anyLong())).thenReturn(getRenter());
    }

    @Test
    public void testCreateRental() throws Exception {
        CreateRentalRequest createRentalRequest = new CreateRentalRequest();
        createRentalRequest.setRenterId(12L);
        createRentalRequest.setVehicleId(125L);
        createRentalRequest.setScheduledPickUpDate("2021-08-25 15:53:16");
        createRentalRequest.setScheduledDropOffDate("2021-08-28 15:53:16");

        vehicleRentalHandler.createRental(createRentalRequest);

        Mockito.verify(vehicleRentalRepository, Mockito.times(1)).save(Mockito.any(VehicleRental.class));
        Mockito.verify(vehicleRepository, Mockito.times(1)).save(Mockito.any(Vehicle.class));
        Mockito.verify(texter, Mockito.times(1)).sendText(Mockito.anyString(), Mockito.anyString());
        Mockito.verify(scheduler, Mockito.times(1)).schedule(Mockito.anyInt(), Mockito.any(Function.class), Mockito.any(String[].class));
    }

    @Test
    public void testPickUpRental() throws Exception {
        Mockito.when(vehicleRentalRepository.getById(Mockito.anyLong())).thenReturn(getVehicleRental());

        vehicleRentalHandler.pickUpVehicle(1254L, "2021-08-25 15:53:16");

        Mockito.verify(vehicleRentalRepository, Mockito.times(1)).save(Mockito.any(VehicleRental.class));
        Mockito.verify(vehicleRepository, Mockito.times(1)).save(Mockito.any(Vehicle.class));
        Mockito.verify(texter, Mockito.times(1)).sendText(Mockito.anyString(), Mockito.anyString());
        Mockito.verify(scheduler, Mockito.times(1)).schedule(Mockito.anyInt(), Mockito.any(Function.class), Mockito.any(String[].class));
        Mockito.verify(payment, Mockito.times(1)).process(Mockito.anyString(), Mockito.anyFloat(), Mockito.anyString());
    }

    @Test
    public void testDropOffRentalOnTimeOnTime() throws Exception {
        Mockito.when(vehicleRentalRepository.getById(Mockito.anyLong())).thenReturn(getVehicleRental());

        vehicleRentalHandler.dropOffVehicle(1254L, "2021-08-28 15:53:16");

        Mockito.verify(vehicleRentalRepository, Mockito.times(1)).save(Mockito.any(VehicleRental.class));
        Mockito.verify(vehicleRepository, Mockito.times(1)).save(Mockito.any(Vehicle.class));
        Mockito.verify(texter, Mockito.times(1)).sendText(Mockito.anyString(), Mockito.anyString());
        Mockito.verify(payment, Mockito.times(0)).process(Mockito.anyString(), Mockito.anyFloat(), Mockito.anyString());
    }

    @Test
    public void testDropOffRentalTimeAfterDropOffDate() throws Exception{
        Mockito.when(vehicleRentalRepository.getById(Mockito.anyLong())).thenReturn(getVehicleRental());

        vehicleRentalHandler.dropOffVehicle(1254L, "2021-08-29 15:53:16");

        Mockito.verify(vehicleRentalRepository, Mockito.times(1)).save(Mockito.any(VehicleRental.class));
        Mockito.verify(vehicleRepository, Mockito.times(1)).save(Mockito.any(Vehicle.class));
        Mockito.verify(texter, Mockito.times(2)).sendText(Mockito.anyString(), Mockito.anyString());
        Mockito.verify(payment, Mockito.times(1)).process(Mockito.anyString(), Mockito.anyFloat(), Mockito.anyString());
    }

    @Test
    public void testCancelRentalWithNoRate() throws Exception {
        Mockito.when(vehicleRentalRepository.getById(Mockito.anyLong())).thenReturn(getVehicleRental());

        vehicleRentalHandler.cancelRental(1254L, "2021-08-20 15:53:16");

        Mockito.verify(vehicleRentalRepository, Mockito.times(1)).save(Mockito.any(VehicleRental.class));
        Mockito.verify(vehicleRepository, Mockito.times(1)).save(Mockito.any(Vehicle.class));
        Mockito.verify(texter, Mockito.times(1)).sendText(Mockito.anyString(), Mockito.anyString());
        Mockito.verify(payment, Mockito.times(0)).process(Mockito.anyString(), Mockito.anyFloat(), Mockito.anyString());
    }

    @Test
    public void testCancelRentalWithRate() throws Exception {
        Mockito.when(vehicleRentalRepository.getById(Mockito.anyLong())).thenReturn(getVehicleRental());

        vehicleRentalHandler.cancelRental(1254L, "2021-08-24 15:53:16");

        Mockito.verify(vehicleRentalRepository, Mockito.times(1)).save(Mockito.any(VehicleRental.class));
        Mockito.verify(vehicleRepository, Mockito.times(1)).save(Mockito.any(Vehicle.class));
        Mockito.verify(texter, Mockito.times(1)).sendText(Mockito.anyString(), Mockito.anyString());
        Mockito.verify(payment, Mockito.times(1)).process(Mockito.anyString(), Mockito.anyFloat(), Mockito.anyString());
    }

    private Renter getRenter() {
        Renter renter = new Renter();
        renter.setTelephoneNumber("305656644");
        renter.setCreditCardNumber("30565664434343");
        return renter;
    }

    private VehicleRental getVehicleRental() {
        VehicleRental vehicleRental = new VehicleRental();
        vehicleRental.setId(1212L);
        vehicleRental.setVehicleId(122L);
        vehicleRental.setRenterId(1221L);
        vehicleRental.setRate(1454);
        vehicleRental.setScheduledDropOffDate(Timestamp.valueOf("2021-08-28 15:53:16"));
        vehicleRental.setScheduledPickUpDate(Timestamp.valueOf("2021-08-25 15:53:16"));

        return vehicleRental;
    }
}
