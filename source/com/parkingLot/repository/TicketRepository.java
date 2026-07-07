package com.parkingLot.repository;

import com.parkingLot.pojo.Ticket;

import java.util.Optional;
import java.util.UUID;

public interface TicketRepository {

    void saveTicket(Ticket ticket);

    Ticket getTicket(UUID ticketId);

    Optional<Ticket> getActiveTicketByVehicleNumber(String vehicleNumber);

    void updateTicket(Ticket ticket);

    void deleteTicket(UUID ticketId);
}
