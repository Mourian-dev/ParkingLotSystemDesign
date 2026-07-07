package com.parkingLot.service;

import com.parkingLot.pojo.Ticket;
import com.parkingLot.pojo.Vehicle;
import com.parkingLot.repository.TicketRepository;
import com.parkingLot.strategy.PricingStrategy;
import com.parkingLot.strategy.SpotAssignmentStrategy;

public class ParkingService {
    private final SpotAssignmentStrategy spotAssignmentStrategy;
    private final TicketRepository ticketRepository;
    private final PricingStrategy pricingStrategy;

    public ParkingService(SpotAssignmentStrategy spotAssignmentStrategy,TicketRepository ticketRepository, PricingStrategy pricingStrategy) {
        this.spotAssignmentStrategy = spotAssignmentStrategy;
        this.ticketRepository = ticketRepository;
        this.pricingStrategy = pricingStrategy;
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        Ticket ticket = spotAssignmentStrategy.assignSpot(vehicle);
        ticketRepository.saveTicket(ticket);
        return ticket;
    }

    public double unparkVehicle(Ticket ticket) {
        ticket.setExitTime(System.currentTimeMillis());
        double price = pricingStrategy.calculatePrice(ticket);
        ticketRepository.updateTicket(ticket);
        return price;
    }
}
