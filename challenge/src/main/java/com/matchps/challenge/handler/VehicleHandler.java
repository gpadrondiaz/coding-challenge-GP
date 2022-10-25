package com.matchps.challenge.handler;

import com.matchps.challenge.constants.Constants;
import com.matchps.challenge.entities.Vehicle;
import com.matchps.challenge.model.VehicleModel;
import com.matchps.challenge.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VehicleHandler {
    @Autowired
    private VehicleRepository vehicleRepository;

    public List<VehicleModel> getAvailableVehicle() {
        List<VehicleModel> vehicleModelList = new ArrayList<>();
        List<Vehicle> vehicles = vehicleRepository.findByState(Constants.VehicleState.AVAILABLE.toString());
        vehicleModelList = vehicles.stream().map((vehicle -> {
            VehicleModel vehicleModel = new VehicleModel();
            vehicleModel.setState(vehicle.getState());
            vehicleModel.setLicensePlate(vehicle.getLicensePlate());
            vehicleModel.setYear(vehicle.getYear());
            return vehicleModel;
        })).collect(Collectors.toList());
        return vehicleModelList;
    }

    public List<VehicleModel> getNoAvailableVehicle() {
        List<VehicleModel> vehicleModelList = new ArrayList<>();
        List<Vehicle> vehicles = vehicleRepository.findByStateNot(Constants.VehicleState.AVAILABLE.toString());
        vehicleModelList = vehicles.stream().map((vehicle -> {
            VehicleModel vehicleModel = new VehicleModel();
            vehicleModel.setState(vehicle.getState());
            vehicleModel.setLicensePlate(vehicle.getLicensePlate());
            vehicleModel.setYear(vehicle.getYear());
            return vehicleModel;
        })).collect(Collectors.toList());
        return vehicleModelList;
    }
}
