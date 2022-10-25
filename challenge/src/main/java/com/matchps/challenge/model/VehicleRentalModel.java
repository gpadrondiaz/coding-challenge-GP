package com.matchps.challenge.model;

public class VehicleRentalModel {
    private Long id;
    private Long renterId;
    private Long vehicleId;
    private String scheduledPickUpDate;
    private String scheduledDropOffDate;
    private String pickUpDate;
    private String propOffDate;
    private String state;
    private double rate;
    private double finalRate;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(String pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public String getPropOffDate() {
        return propOffDate;
    }

    public void setPropOffDate(String propOffDate) {
        this.propOffDate = propOffDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
