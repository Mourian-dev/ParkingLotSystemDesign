package com.parkingLot.exception;

import com.parkingLot.pojo.VehicleType;

public class SpotUnavailableException extends ParkingException {
    public SpotUnavailableException(VehicleType vehicleType) {
        super("No available spot for vehicle type: " + vehicleType);
    }
}
