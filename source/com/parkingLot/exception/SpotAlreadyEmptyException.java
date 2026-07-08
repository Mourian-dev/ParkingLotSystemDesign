package com.parkingLot.exception;

public class SpotAlreadyEmptyException extends ParkingException {

    private SpotAlreadyEmptyException(String message) {
        super(message);
    }

    public static SpotAlreadyEmptyException forSpot(int slotNo, int floorNo) {
        return new SpotAlreadyEmptyException("Spot (floor=" + floorNo + ", slot=" + slotNo + ") is already empty.");
    }
}
