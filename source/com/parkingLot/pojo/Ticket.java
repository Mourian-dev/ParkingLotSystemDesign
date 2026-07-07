package com.parkingLot.pojo;

import java.util.UUID;

public class Ticket {
    private final UUID ticketId;
    private final Vehicle vehicle;
    private final Spot spot;
    private final Long entryTime;
    private Long exitTime;

    public Ticket(UUID ticketId, Vehicle vehicle, Spot spot) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.spot = spot;
        this.entryTime = System.currentTimeMillis();
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Spot getSlot() {
        return spot;
    }

    public Long getEntryTime() {
        return entryTime;
    }

    public Long getExitTime() {
        return exitTime;
    }

    public void setExitTime(Long exitTime) {
        this.exitTime = exitTime;
    }
}
