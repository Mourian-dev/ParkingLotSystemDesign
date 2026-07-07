package com.parkingLot.exception;

import java.util.UUID;

public class InvalidTicketException extends ParkingException {
    public InvalidTicketException(String message) {
        super(message);
    }

    public static InvalidTicketException alreadyClosed(UUID ticketId) {
        return new InvalidTicketException("Ticket is already closed: " + ticketId);
    }
}
