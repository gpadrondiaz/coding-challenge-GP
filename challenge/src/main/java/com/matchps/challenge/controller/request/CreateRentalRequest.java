package com.matchps.challenge.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateRentalRequest {
    @NotNull(message = "Renter required")
    private Long renterId;

    @NotNull(message = "Vehicle required")
    private Long vehicleId;

    @NotNull(message = "Scheduled PickUp Date required")
    @NotBlank(message = "Scheduled PickUp Date required")
    private String scheduledPickUpDate;

    @NotNull(message = "Scheduled DropOff Date required")
    @NotBlank(message = "Scheduled DropOff Date required")
    private String scheduledDropOffDate;

    public Long getRenterId() {
        return renterId;
    }

    public void setRenterId(Long renterId) {
        this.renterId = renterId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getScheduledPickUpDate() {
        return scheduledPickUpDate;
    }

    public void setScheduledPickUpDate(String scheduledPickUpDate) {
        this.scheduledPickUpDate = scheduledPickUpDate;
    }

    public String getScheduledDropOffDate() {
        return scheduledDropOffDate;
    }

    public void setScheduledDropOffDate(String scheduledDropOffDate) {
        this.scheduledDropOffDate = scheduledDropOffDate;
    }
}
