package com.matchps.challenge.repository;

import com.matchps.challenge.entities.Vehicle;
import com.matchps.challenge.entities.VehicleRental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRentalRepository extends JpaRepository<VehicleRental, Long> {
    List<VehicleRental> findByStateNotIn(List<String> state);

}
