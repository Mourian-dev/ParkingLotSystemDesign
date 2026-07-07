package com.parkingLot.strategy;

import com.parkingLot.pojo.Ticket;

public class HourlyPricingStrategy implements PricingStrategy {

    private final double baseHourlyRate;

    public HourlyPricingStrategy(double baseHourlyRate) {
        if (baseHourlyRate <= 0) {
            throw new IllegalArgumentException("Hourly rate must be > 0.");
        }
        this.baseHourlyRate = baseHourlyRate;
    }

    @Override
    public double calculatePrice(Ticket ticket) {
        if (ticket.getExitTime() == null) {
            throw new IllegalStateException("Cannot price an open ticket.");
        }

        long durationMillis = ticket.getExitTime() - ticket.getEntryTime();
        long billableHours = Math.max(1, (long) Math.ceil(durationMillis / 3600000.0));
        return billableHours * baseHourlyRate;
    }
}

