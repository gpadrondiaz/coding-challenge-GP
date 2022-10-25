package com.matchps.challenge.controller.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

public class DropOffOrPickUpVehicleRequest {
    @NotNull(message = "Vehicle rent id required")
    private Long id;

    @NotNull(message = "Date required")
    @NotBlank(message = "Date required")
    private String date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
