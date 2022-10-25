package com.matchps.challenge.model;

import java.sql.Timestamp;

public class VehicleModel {
    private Long id;
    private String licensePlate;
    private String state;
    private Boolean archived;
    private Integer year;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public VehicleModel() {
    }

    public VehicleModel(String licensePlate, String state, Boolean archived, Integer year, Timestamp createdAt) {
        this.licensePlate = licensePlate;
        this.state = state;
        this.archived = archived;
        this.year = year;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
