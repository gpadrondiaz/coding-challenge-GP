package com.matchps.challenge.constants;

public class Constants {
    //VEHICLE RENTAL STATES

    public enum VehicleRentalState {
        SCHEDULED,
        PICKED_UP,
        DROPPED_OFF,
        CANCELLED;
    }

    public enum VehicleState {
        AVAILABLE,
        RESERVED,
        RENTED;
    }

}
