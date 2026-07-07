package com.parkingLot.observer;

import com.parkingLot.pojo.Ticket;

public class ParkingEvent {
    private final ParkingEventType type;
    private final Ticket ticket;
    private final long timestamp;

    public ParkingEvent(ParkingEventType type, Ticket ticket, long timestamp) {
        this.type = type;
        this.ticket = ticket;
        this.timestamp = timestamp;
    }

    public ParkingEventType getType() {
        return type;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
