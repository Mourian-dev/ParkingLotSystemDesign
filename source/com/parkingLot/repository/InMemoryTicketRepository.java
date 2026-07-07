package com.parkingLot.repository;

import com.parkingLot.pojo.Ticket;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryTicketRepository implements TicketRepository {

    private final Map<UUID, Ticket> ticketStore = new ConcurrentHashMap<>();

    @Override
    public void saveTicket(Ticket ticket) {
        ticketStore.put(ticket.getTicketId(), ticket);
    }

    @Override
    public Ticket getTicket(UUID ticketId) {
        return ticketStore.get(ticketId);
    }

    @Override
    public Optional<Ticket> getActiveTicketByVehicleNumber(String vehicleNumber) {
        return ticketStore.values().stream()
                .filter(ticket -> ticket.getVehicle().getVehicleNumber().equals(vehicleNumber))
                .filter(ticket -> ticket.getExitTime() == null)
                .findFirst();
    }

    @Override
    public void updateTicket(Ticket ticket) {
        ticketStore.put(ticket.getTicketId(), ticket);
    }

    @Override
    public void deleteTicket(UUID ticketId) {
        ticketStore.remove(ticketId);
    }
}

