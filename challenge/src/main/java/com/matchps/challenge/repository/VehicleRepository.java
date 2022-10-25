package com.matchps.challenge.repository;

import com.matchps.challenge.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
     List<Vehicle> findByState(String state);
     List<Vehicle> findByStateNot(String state);
}
