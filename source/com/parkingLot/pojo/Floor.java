package com.parkingLot.pojo;

import com.parkingLot.util.Validator;

import java.util.List;
import java.util.stream.Collectors;

public class Floor {
    private final int floorNo;
    private final List<Spot> spots;

    private Floor(int floorNo, List<Spot> spots) {
        Validator.requireNonNegative(floorNo, "floorNo");
        Validator.requireNonEmpty(spots, "spots");
        this.floorNo = floorNo;
        this.spots = spots;
    }

    public static Floor of(int floorNo, List<Spot> spots) {
        return new Floor(floorNo, spots);
    }

    public int getFloorNo() {
        return floorNo;
    }

    public List<Spot> getSpots() {
        return spots;
    }

    public List<Spot> getSpotsByType(VehicleType vehicleType) {
        return spots.stream()
                .filter(spot -> spot.getVehicleType() == vehicleType)
                .collect(Collectors.toList());
    }
}
