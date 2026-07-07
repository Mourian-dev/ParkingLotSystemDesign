package com.parkingLot.strategy;

import com.parkingLot.exception.SpotUnavailableException;
import com.parkingLot.pojo.Spot;
import com.parkingLot.pojo.Ticket;
import com.parkingLot.pojo.Vehicle;
import com.parkingLot.repository.SpotRepository;

public class NearestSpotAssignmentStrategy implements SpotAssignmentStrategy {

    private final SpotRepository spotRepository;

    public NearestSpotAssignmentStrategy(SpotRepository spotRepository) {
        this.spotRepository = spotRepository;
    }

    @Override
    public Ticket assignSpot(Vehicle vehicle) {
        while (true) {
            Spot candidate = spotRepository.findAvailableSpot(vehicle.getVehicleType())
                    .orElseThrow(() -> new SpotUnavailableException(vehicle.getVehicleType()));

            if (candidate.tryPark(vehicle)) {
                spotRepository.updateSpot(candidate);
                return Ticket.create(vehicle, candidate);
            }
        }
    }
}
