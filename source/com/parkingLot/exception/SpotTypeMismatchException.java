package com.parkingLot.exception;

import com.parkingLot.pojo.VehicleType;

public class SpotTypeMismatchException extends ParkingException {

    private SpotTypeMismatchException(String message) {
        super(message);
    }

    public static SpotTypeMismatchException of(VehicleType vehicleType, VehicleType spotType) {
        return new SpotTypeMismatchException(
                "Vehicle type " + vehicleType + " does not match spot type " + spotType);
    }
}
