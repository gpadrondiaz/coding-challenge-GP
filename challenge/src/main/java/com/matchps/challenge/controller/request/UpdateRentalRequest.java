package com.matchps.challenge.controller.request;

import javax.validation.constraints.NotNull;

public class UpdateRentalRequest {
    @NotNull(message = "Rental id required")
    private Long id;
    private String scheduledPickUpDate;
    private String scheduledDropOffDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
