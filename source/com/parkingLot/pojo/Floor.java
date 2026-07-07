package com.parkingLot.pojo;

import java.util.List;

public class Floor {
    private final int floorNo;
    private final List<Spot> spots;

    public Floor(int floorNo,List<Spot> spots) {
        this.floorNo = floorNo;
        this.spots = spots;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public List<Spot> getSlots() {
        return spots;
    }
}
