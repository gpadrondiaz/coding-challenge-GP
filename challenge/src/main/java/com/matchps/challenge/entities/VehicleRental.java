package com.matchps.challenge.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="\"vehicle_rental\"", schema ="\"uber_car_rental\"")
public class VehicleRental implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="\"vehicle_rental_id\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="\"renter_id\"")
    @NotNull(message = "Renter required")
    private Long renterId;

    @Column(name="\"vehicle_id\"")
    @NotNull(message = "Vehicle required")
    private Long vehicleId;

    @Column(name="\"scheduled_pickup_date\"")
    @NotNull(message = "Scheduled PickUp Date required")
    private Timestamp scheduledPickUpDate;

    @Column(name="\"scheduled_dropoff_date\"")
    @NotNull(message = "Scheduled DropOff Date required")
    private Timestamp scheduledDropOffDate;

    @Column(name="\"final_pickup_date\"")
    private Timestamp finalPickUpDate;

    @Column(name="\"final_dropoff_date\"")
    private Timestamp finalDropOffDate;

    @Column(name="\"created_at\"")
    private Timestamp createdAt;

    @Column(name="\"updated_at\"")
    private Timestamp updatedAt;

    @Column()
    private String state;

    @Column()
    private double rate;

    @Column(name="\"final_rate\"")
    private double finalRate;

    @OneToOne
    @JoinColumns({@JoinColumn(name = "\"vehicle\"", insertable = false, updatable = false)
    })
    private Vehicle vehicle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Timestamp getScheduledPickUpDate() {
        return scheduledPickUpDate;
    }

    public void setScheduledPickUpDate(Timestamp scheduledPickUpDate) {
        this.scheduledPickUpDate = scheduledPickUpDate;
    }

    public Timestamp getScheduledDropOffDate() {
        return scheduledDropOffDate;
    }

    public void setScheduledDropOffDate(Timestamp scheduledDropOffDate) {
        this.scheduledDropOffDate = scheduledDropOffDate;
    }

    public Timestamp getFinalPickUpDate() {
        return finalPickUpDate;
    }

    public void setFinalPickUpDate(Timestamp finalPickUpDate) {
        this.finalPickUpDate = finalPickUpDate;
    }

    public Timestamp getFinalDropOffDate() {
        return finalDropOffDate;
    }

    public void setFinalDropOffDate(Timestamp finalDropOffDate) {
        this.finalDropOffDate = finalDropOffDate;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getFinalRate() {
        return finalRate;
    }

    public void setFinalRate(double finalRate) {
        this.finalRate = finalRate;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
