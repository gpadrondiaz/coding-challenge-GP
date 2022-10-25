package com.matchps.challenge.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * The persistent class for the "VEHICLE" database table.
 *
 */
@Entity
@Table(name="\"vehicle\"", schema ="\"uber_car_rental\"")
public class Vehicle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"vehicle_id\"")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="\"license_plate\"")
	private String licensePlate;

	@Column()
	private String state;

	@Column()
	private Boolean archived;

	@Column()
	private Integer year;

	@Column(name="\"created_at\"")
	private Timestamp createdAt;

	@Column(name="\"updated_at\"")
	private Timestamp updatedAt;

	public Vehicle() {
	}

	public Vehicle(String licensePlate, String state, Boolean archived, Integer year, Timestamp createdAt) {
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
