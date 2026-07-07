package com.parkingLot.repository;

import com.parkingLot.pojo.Floor;
import com.parkingLot.pojo.ParkingLot;
import com.parkingLot.pojo.Spot;
import com.parkingLot.pojo.VehicleType;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemorySpotRepository implements SpotRepository {

    private final Map<String, Spot> spotStore = new ConcurrentHashMap<>();

    public InMemorySpotRepository(ParkingLot parkingLot) {
        for (Floor floor : parkingLot.getFloors()) {
            for (Spot spot : floor.getSpots()) {
                saveSpot(spot);
            }
        }
    }

    @Override
    public Spot getSpot(int spotNo, int floorNo) {
        return spotStore.get(key(spotNo, floorNo));
    }

    @Override
    public Optional<Spot> findAvailableSpot(VehicleType vehicleType) {
        return spotStore.values().stream()
                .filter(spot -> !spot.isOccupied() && spot.getVehicleType() == vehicleType)
                .sorted((left, right) -> {
                    int floorCompare = Integer.compare(left.getFloorNo(), right.getFloorNo());
                    if (floorCompare != 0) {
                        return floorCompare;
                    }
                    return Integer.compare(left.getSlotNo(), right.getSlotNo());
                })
                .findFirst();
    }

    @Override
    public void updateSpot(Spot spot) {
        saveSpot(spot);
    }

    @Override
    public void saveSpot(Spot spot) {
        spotStore.put(key(spot.getSlotNo(), spot.getFloorNo()), spot);
    }

    @Override
    public void deleteSpot(int spotNo, int floorNo) {
        spotStore.remove(key(spotNo, floorNo));
    }

    private String key(int spotNo, int floorNo) {
        return floorNo + "-" + spotNo;
    }
}

