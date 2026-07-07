package com.parkingLot.strategy;

import com.parkingLot.factory.TicketFactory;
import com.parkingLot.pojo.Spot;
import com.parkingLot.pojo.Ticket;
import com.parkingLot.pojo.Vehicle;
import com.parkingLot.repository.SpotRepository;

public class NearestSpotAssignmentStrategy implements SpotAssignmentStrategy {

    private final SpotRepository spotRepository;
    private final TicketFactory ticketFactory;

    public NearestSpotAssignmentStrategy(SpotRepository spotRepository, TicketFactory ticketFactory) {
        this.spotRepository = spotRepository;
        this.ticketFactory = ticketFactory;
    }

    @Override
    public Ticket assignSpot(Vehicle vehicle) {
        Spot availableSpot = spotRepository.findAvailableSpot(vehicle.getVehicleType())
                .orElseThrow(() -> new IllegalStateException("No spot available for vehicle type: " + vehicle.getVehicleType()));

        availableSpot.park(vehicle);
        spotRepository.updateSpot(availableSpot);
        return ticketFactory.createTicket(vehicle, availableSpot);
    }
}

