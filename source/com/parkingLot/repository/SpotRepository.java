package com.parkingLot.repository;

import com.parkingLot.pojo.Spot;
import com.parkingLot.pojo.VehicleType;

import java.util.Optional;

public interface SpotRepository {
    Spot getSpot(int spotNo, int floorNo);
    Optional<Spot> findAvailableSpot(VehicleType vehicleType);
    void updateSpot(Spot spot);
    void saveSpot(Spot spot);
    void deleteSpot(int spotNo, int floorNo);
}
