package com.parkingLot.repository;

import com.parkingLot.pojo.Spot;

public interface SpotRepository {
    Spot getSpot(int spotNo, int floorNo);
    void updateSpot(Spot spot);
    void saveSpot(Spot spot);
    void deleteSpot(int spotNo, int floorNo);
}
