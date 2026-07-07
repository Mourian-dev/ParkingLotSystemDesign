package com.parkingLot.pojo;

import java.util.List;

public class Floor {
    private final int floorNo;
    private final List<Spot> spots;

    public Floor(int floorNo,List<Spot> spots) {
        if (floorNo < 0 || spots == null) {
            throw new IllegalArgumentException("Invalid floor configuration.");
        }
        this.floorNo = floorNo;
        this.spots = spots;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public List<Spot> getSpots() {
        return spots;
    }
}
