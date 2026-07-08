package com.parkingLot.repository;

import com.parkingLot.pojo.ParkingLot;
import com.parkingLot.pojo.Spot;
import com.parkingLot.pojo.VehicleType;

import java.util.Optional;

public interface SpotRepository {
    static SpotRepository getInstance(ParkingLot parkingLot) {
        return InMemorySpotRepository.getInstance(parkingLot);
    }
    Spot getSpot(int spotNo, int floorNo);
    Optional<Spot> findAvailableSpot(VehicleType vehicleType);
    void updateSpot(Spot spot);
    void saveSpot(Spot spot);
}
