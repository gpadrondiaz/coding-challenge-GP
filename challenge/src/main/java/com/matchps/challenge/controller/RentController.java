package com.matchps.challenge.controller;

import com.matchps.challenge.controller.request.CreateRentalRequest;
import com.matchps.challenge.controller.request.DropOffOrPickUpVehicleRequest;
import com.matchps.challenge.controller.request.UpdateRentalRequest;
import com.matchps.challenge.handler.VehicleHandler;
import com.matchps.challenge.handler.VehicleRentalHandler;
import com.matchps.challenge.model.NoAvailableVehicleStock;
import com.matchps.challenge.model.VehicleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("vehicleRent")
public class RentController {

    @Autowired
    private VehicleRentalHandler vehicleRentalHandler;

    @Autowired
    private VehicleHandler vehicleHandler;

    @RequestMapping(value = "/createRental", method = RequestMethod.POST)
    public ResponseEntity<String> createRental(@Valid @RequestBody CreateRentalRequest createRentalRequest, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        try {
            vehicleRentalHandler.createRental(createRentalRequest);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @RequestMapping(value = "/pickUpVehicle", method = RequestMethod.POST)
    public ResponseEntity<String> pickUpVehicle(@Valid @RequestBody DropOffOrPickUpVehicleRequest dropOffOrPickUpVehicleRequest, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            vehicleRentalHandler.pickUpVehicle(dropOffOrPickUpVehicleRequest.getId(), dropOffOrPickUpVehicleRequest.getDate());
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @RequestMapping(value = "/dropOffVehicle", method = RequestMethod.POST)
    public ResponseEntity<String> dropOffVehicle(@Valid @RequestBody DropOffOrPickUpVehicleRequest dropOffOrPickUpVehicleRequest, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            vehicleRentalHandler.dropOffVehicle(dropOffOrPickUpVehicleRequest.getId(), dropOffOrPickUpVehicleRequest.getDate());
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @RequestMapping(value = "/cancelRental", method = RequestMethod.POST)
    public ResponseEntity<String> cancelRental(@Valid @RequestBody DropOffOrPickUpVehicleRequest dropOffOrPickUpVehicleRequest, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
        vehicleRentalHandler.cancelRental(dropOffOrPickUpVehicleRequest.getId(), dropOffOrPickUpVehicleRequest.getDate());
        return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @RequestMapping(value = "/updateRental", method = RequestMethod.POST)
    public ResponseEntity<String> updateRental(@Valid @RequestBody UpdateRentalRequest updateRentalRequest, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        try {
            vehicleRentalHandler.updateRental(updateRentalRequest.getId(), updateRentalRequest.getScheduledDropOffDate(), updateRentalRequest.getScheduledDropOffDate());
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @RequestMapping(value = "/getAvailableVehicles", method = RequestMethod.GET)
    public ResponseEntity<List<VehicleModel>> getAvailableVehicles() {
        try {
            List<VehicleModel> result = vehicleHandler.getAvailableVehicle();
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @RequestMapping(value = "/getNoAvailableVehicles", method = RequestMethod.GET)
    public ResponseEntity<List<NoAvailableVehicleStock>> getNoAvailableVehicles() {
        try {
            List<NoAvailableVehicleStock> result = vehicleRentalHandler.getNoAvailableVehicle();
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
