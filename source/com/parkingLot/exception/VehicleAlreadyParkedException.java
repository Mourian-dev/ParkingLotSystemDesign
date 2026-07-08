package com.parkingLot.exception;

import java.util.UUID;

public class VehicleAlreadyParkedException extends ParkingException {

    private VehicleAlreadyParkedException(String message) {
        super(message);
    }

    public static VehicleAlreadyParkedException forVehicle(String vehicleNumber, UUID ticketId) {
        return new VehicleAlreadyParkedException(
                "Vehicle " + vehicleNumber + " is already parked with ticket: " + ticketId);
    }
}
