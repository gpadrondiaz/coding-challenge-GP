package com.matchps.challenge.repository;

import com.matchps.challenge.entities.Renter;
import com.matchps.challenge.entities.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private RenterRepository renterRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public void run(String... args) throws Exception {
        loadRenterData();
        loadVehicleData();
    }

    private void loadRenterData() {
        renterRepository.deleteAll();
        if (renterRepository.count() == 0) {
            Renter renter1 = new Renter("123456", "John", "Matheus", "305789456", "john@john.com", "5152698523145588");
            Renter renter2 = new Renter("789012", "Mary", "Lan", "306125863", "mary@mary.com", "4052698523145588");
            renterRepository.save(renter1);
            renterRepository.save(renter2);
        }
        System.out.println("Saved renters: " + renterRepository.count());
    }

    private void loadVehicleData() {
        vehicleRepository.deleteAll();
        if (vehicleRepository.count() == 0) {
            Date date = new Date();
            long time = date.getTime();
            Timestamp createdAt = new Timestamp(time);
            Vehicle vehicle1 = new Vehicle("ADD-3587", "AVAILABLE", false, 2020, createdAt);
            Vehicle vehicle2 = new Vehicle("BFC-3585", "AVAILABLE", false, 2021, createdAt);
            Vehicle vehicle3 = new Vehicle("BFC-3581", "AVAILABLE", false, 2020, createdAt);

            vehicleRepository.save(vehicle1);
            vehicleRepository.save(vehicle2);
            vehicleRepository.save(vehicle3);
        }
        System.out.println("Saved vehicles: " + vehicleRepository.count());
    }
}
