package com.parkingLot.exception;

import com.parkingLot.pojo.VehicleType;

public class SpotUnavailableException extends ParkingException {

    private SpotUnavailableException(String message) {
        super(message);
    }

    public static SpotUnavailableException forVehicleType(VehicleType vehicleType) {
        return new SpotUnavailableException("No available spot for vehicle type: " + vehicleType);
    }
}
