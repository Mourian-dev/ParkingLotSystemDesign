package com.parkingLot.repository;

import com.parkingLot.pojo.Ticket;
import com.parkingLot.util.Validator;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryTicketRepository implements TicketRepository {

    private static InMemoryTicketRepository instance;

    private final Map<UUID, Ticket> ticketStore = new ConcurrentHashMap<>();

    static TicketRepository getInstance() {
        if (instance == null) {
            instance = new InMemoryTicketRepository();
        }
        return instance;
    }

    @Override
    public void saveTicket(Ticket ticket) {
        Validator.requireNonNull(ticket, "ticket");
        ticketStore.put(ticket.getTicketId(), ticket);
    }

    @Override
    public Ticket getTicket(UUID ticketId) {
        Validator.requireNonNull(ticketId, "ticketId");
        return ticketStore.get(ticketId);
    }

    @Override
    public Optional<Ticket> getActiveTicketByVehicleNumber(String vehicleNumber) {
        Validator.requireNonNull(vehicleNumber, "vehicleNumber");
        return ticketStore.values().stream()
                .filter(ticket -> ticket.getVehicle().getVehicleNumber().equals(vehicleNumber))
                .filter(ticket -> ticket.getExitTime() == null)
                .findFirst();
    }

    @Override
    public void updateTicket(Ticket ticket) {
        Validator.requireNonNull(ticket, "ticket");
        ticketStore.put(ticket.getTicketId(), ticket);
    }
}
