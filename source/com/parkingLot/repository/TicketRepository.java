package com.parkingLot.repository;

import com.parkingLot.pojo.Ticket;

public interface TicketRepository {

    void saveTicket(Ticket ticket);

    Ticket getTicket(String ticketId);

    void updateTicket(Ticket ticket);

    void deleteTicket(String ticketId);
}
