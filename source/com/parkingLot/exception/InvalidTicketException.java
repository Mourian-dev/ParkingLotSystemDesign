package com.parkingLot.exception;

import java.util.UUID;

public class InvalidTicketException extends ParkingException {

    private InvalidTicketException(String message) {
        super(message);
    }

    public static InvalidTicketException alreadyClosed(UUID ticketId) {
        return new InvalidTicketException("Ticket is already closed: " + ticketId);
    }

    public static InvalidTicketException stillOpen(UUID ticketId) {
        return new InvalidTicketException("Ticket is still open: " + ticketId);
    }

    public static InvalidTicketException invalidExitTime() {
        return new InvalidTicketException("exitTime must be >= entryTime.");
    }
}
