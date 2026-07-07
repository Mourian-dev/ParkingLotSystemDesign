package com.parkingLot.factory;

import com.parkingLot.pojo.Spot;
import com.parkingLot.pojo.Ticket;
import com.parkingLot.pojo.Vehicle;

import java.util.UUID;

public class TicketFactory {

    public Ticket createTicket(UUID ticketId, Vehicle vehicle, Spot spot) {
        return new Ticket(ticketId, vehicle, spot);
    }

    public Ticket createTicket(Vehicle vehicle, Spot spot) {
        UUID ticketId = UUID.randomUUID();
        return new Ticket(ticketId, vehicle, spot);
    }
}
