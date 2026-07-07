package com.parkingLot.service;

import com.parkingLot.pojo.Payment;
import com.parkingLot.pojo.PaymentMethod;
import com.parkingLot.pojo.Ticket;
import com.parkingLot.pojo.Vehicle;
import com.parkingLot.repository.SpotRepository;
import com.parkingLot.repository.TicketRepository;
import com.parkingLot.strategy.PricingStrategy;
import com.parkingLot.strategy.SpotAssignmentStrategy;

public class ParkingService {
    private final SpotAssignmentStrategy spotAssignmentStrategy;
    private final TicketRepository ticketRepository;
    private final SpotRepository spotRepository;
    private final PricingStrategy pricingStrategy;
    private final PaymentService paymentService;

    public ParkingService(
            SpotAssignmentStrategy spotAssignmentStrategy,
            TicketRepository ticketRepository,
            SpotRepository spotRepository,
            PricingStrategy pricingStrategy,
            PaymentService paymentService) {
        this.spotAssignmentStrategy = spotAssignmentStrategy;
        this.ticketRepository = ticketRepository;
        this.spotRepository = spotRepository;
        this.pricingStrategy = pricingStrategy;
        this.paymentService = paymentService;
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        Ticket existingTicket = ticketRepository.getActiveTicketByVehicleNumber(vehicle.getVehicleNumber());
        if (existingTicket != null) {
            throw new IllegalStateException("Vehicle is already parked with ticket: " + existingTicket.getTicketId());
        }

        Ticket ticket = spotAssignmentStrategy.assignSpot(vehicle);
        ticketRepository.saveTicket(ticket);
        return ticket;
    }

    public double unparkVehicle(Ticket ticket) {
        return unparkVehicle(ticket, PaymentMethod.CASH).getAmount();
    }

    public Payment unparkVehicle(Ticket ticket, PaymentMethod paymentMethod) {
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket is required for unpark operation.");
        }
        if (ticket.getExitTime() != null) {
            throw new IllegalStateException("Ticket is already closed: " + ticket.getTicketId());
        }

        ticket.setExitTime(System.currentTimeMillis());
        double price = pricingStrategy.calculatePrice(ticket);

        ticket.getSpot().unPark();
        spotRepository.updateSpot(ticket.getSpot());
        ticketRepository.updateTicket(ticket);

        return paymentService.collect(price, paymentMethod);
    }
}
