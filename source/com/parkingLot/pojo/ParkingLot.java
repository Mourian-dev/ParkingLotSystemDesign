package com.parkingLot.pojo;

import java.util.List;

public class ParkingLot {
    private final List<Floor> floors;

    public ParkingLot(List<Floor> floors) {
        if (floors == null || floors.isEmpty()) {
            throw new IllegalArgumentException("Parking lot must contain at least one floor.");
        }
        this.floors = floors;
    }

    public List<Floor> getFloors() {
        return floors;
    }
}
