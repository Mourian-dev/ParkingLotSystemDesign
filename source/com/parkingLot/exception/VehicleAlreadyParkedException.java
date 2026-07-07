package com.parkingLot.exception;

import java.util.UUID;

public class VehicleAlreadyParkedException extends ParkingException {
    public VehicleAlreadyParkedException(String vehicleNumber, UUID ticketId) {
        super("Vehicle " + vehicleNumber + " is already parked with ticket: " + ticketId);
    }
}
