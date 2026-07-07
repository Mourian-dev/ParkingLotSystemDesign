package com.parkingLot.pojo;

import com.parkingLot.util.Validator;

import java.util.List;

public class ParkingLot {
    private final List<Floor> floors;

    private ParkingLot(List<Floor> floors) {
        Validator.requireNonEmpty(floors, "floors");
        this.floors = floors;
    }

    public static ParkingLot of(List<Floor> floors) {
        return new ParkingLot(floors);
    }

    public List<Floor> getFloors() {
        return floors;
    }
}
