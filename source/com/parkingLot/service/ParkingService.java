package com.parkingLot.service;

import com.parkingLot.exception.InvalidTicketException;
import com.parkingLot.exception.VehicleAlreadyParkedException;
import com.parkingLot.observer.ParkingEvent;
import com.parkingLot.observer.ParkingEventListener;
import com.parkingLot.observer.ParkingEventType;
import com.parkingLot.pojo.Payment;
import com.parkingLot.pojo.PaymentMethod;
import com.parkingLot.pojo.Spot;
import com.parkingLot.pojo.SpotInfo;
import com.parkingLot.pojo.Ticket;
import com.parkingLot.pojo.Vehicle;
import com.parkingLot.repository.SpotRepository;
import com.parkingLot.repository.TicketRepository;
import com.parkingLot.strategy.PricingStrategy;
import com.parkingLot.strategy.SpotAssignmentStrategy;
import com.parkingLot.util.Validator;

import java.util.ArrayList;
import java.util.List;

public class ParkingService {

    private final SpotAssignmentStrategy spotAssignmentStrategy;
    private final TicketRepository ticketRepository;
    private final SpotRepository spotRepository;
    private final PricingStrategy pricingStrategy;
    private final PaymentService paymentService;
    private final List<ParkingEventListener> listeners = new ArrayList<>();

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

    public void registerListener(ParkingEventListener listener) {
        Validator.requireNonNull(listener, "listener");
        listeners.add(listener);
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        Validator.requireNonNull(vehicle, "vehicle");
        ticketRepository.getActiveTicketByVehicleNumber(vehicle.getVehicleNumber())
                .ifPresent(t -> {
                    throw new VehicleAlreadyParkedException(vehicle.getVehicleNumber(), t.getTicketId());
                });

        Ticket ticket = spotAssignmentStrategy.assignSpot(vehicle);
        ticketRepository.saveTicket(ticket);
        publish(ParkingEventType.VEHICLE_PARKED, ticket);
        return ticket;
    }

    public Payment unparkVehicle(Ticket ticket) {
        return unparkVehicle(ticket, PaymentMethod.CASH);
    }

    public Payment unparkVehicle(Ticket ticket, PaymentMethod paymentMethod) {
        Validator.requireNonNull(ticket, "ticket");
        Validator.requireNonNull(paymentMethod, "paymentMethod");

        if (ticket.getExitTime() != null) {
            throw InvalidTicketException.alreadyClosed(ticket.getTicketId());
        }

        ticket.setExitTime(System.currentTimeMillis());
        double price = pricingStrategy.calculatePrice(ticket);

        SpotInfo spotInfo = ticket.getSpotInfo();
        Spot spot = spotRepository.getSpot(spotInfo.getSlotNo(), spotInfo.getFloorNo());
        spot.unPark();
        spotRepository.updateSpot(spot);
        ticketRepository.updateTicket(ticket);

        publish(ParkingEventType.VEHICLE_UNPARKED, ticket);
        return paymentService.collect(price, paymentMethod);
    }

    private void publish(ParkingEventType type, Ticket ticket) {
        ParkingEvent event = new ParkingEvent(type, ticket, System.currentTimeMillis());
        listeners.forEach(l -> l.onEvent(event));
    }
}
