package com.parkingLot.repository;

import com.parkingLot.pojo.ParkingLot;
import com.parkingLot.pojo.Spot;
import com.parkingLot.pojo.VehicleType;
import com.parkingLot.util.Validator;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemorySpotRepository implements SpotRepository {

    private static InMemorySpotRepository instance;

    private final Map<String, Spot> spotStore = new ConcurrentHashMap<>();

    public InMemorySpotRepository(ParkingLot parkingLot) {
        Validator.requireNonNull(parkingLot, "parkingLot");
        parkingLot.getFloors().stream()
                .flatMap(f -> f.getSpots().stream())
                .forEach(this::saveSpot);
    }

    static SpotRepository getInstance(ParkingLot parkingLot) {
        Validator.requireNonNull(parkingLot, "parkingLot");
        if (instance == null) {
            instance = new InMemorySpotRepository(parkingLot);
        }
        return instance;
    }

    @Override
    public Spot getSpot(int spotNo, int floorNo) {
        return spotStore.get(key(spotNo, floorNo));
    }

    @Override
    public Optional<Spot> findAvailableSpot(VehicleType vehicleType) {
        return spotStore.values().stream()
                .filter(spot -> !spot.isOccupied() && spot.getVehicleType() == vehicleType)
                .min(Comparator.comparingInt(Spot::getFloorNo).thenComparingInt(Spot::getSlotNo));
    }

    @Override
    public void updateSpot(Spot spot) {
        saveSpot(spot);
    }

    @Override
    public void saveSpot(Spot spot) {
        spotStore.put(key(spot.getSlotNo(), spot.getFloorNo()), spot);
    }

    private String key(int spotNo, int floorNo) {
        return floorNo + "-" + spotNo;
    }
}
