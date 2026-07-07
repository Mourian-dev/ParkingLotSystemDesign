package com.parkingLot.exception;

import com.parkingLot.pojo.VehicleType;

public class SpotTypeMismatchException extends ParkingException {
    public SpotTypeMismatchException(VehicleType vehicleType, VehicleType spotType) {
        super("Vehicle type " + vehicleType + " does not match spot type " + spotType);
    }
}
