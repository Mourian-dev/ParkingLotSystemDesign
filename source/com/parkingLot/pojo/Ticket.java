package com.parkingLot.pojo;

import com.parkingLot.exception.InvalidInputException;
import com.parkingLot.util.Validator;

import java.util.UUID;

public class Ticket {
    private final UUID ticketId;
    private final Vehicle vehicle;
    private final Spot spot;
    private final long entryTime;
    private Long exitTime;

    private Ticket(UUID ticketId, Vehicle vehicle, Spot spot) {
        Validator.requireNonNull(ticketId, "ticketId");
        Validator.requireNonNull(vehicle, "vehicle");
        Validator.requireNonNull(spot, "spot");
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.spot = spot;
        this.entryTime = System.currentTimeMillis();
    }

    public static Ticket create(Vehicle vehicle, Spot spot) {
        return new Ticket(UUID.randomUUID(), vehicle, spot);
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    Spot getSpot() {
        return spot;
    }

    public SpotInfo getSpotInfo() {
        return SpotInfo.from(spot);
    }

    public long getEntryTime() {
        return entryTime;
    }

    public Long getExitTime() {
        return exitTime;
    }

    public void setExitTime(long exitTime) {
        if (exitTime < entryTime) {
            throw new InvalidInputException("exitTime must be >= entryTime.");
        }
        this.exitTime = exitTime;
    }
}
