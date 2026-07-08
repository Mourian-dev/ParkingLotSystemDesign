package com.parkingLot.strategy;

import com.parkingLot.exception.InvalidTicketException;
import com.parkingLot.pojo.Ticket;
import com.parkingLot.pojo.VehicleType;
import com.parkingLot.util.Validator;

import java.util.EnumMap;
import java.util.Map;

public class HourlyPricingStrategy implements PricingStrategy {

    private final double defaultHourlyRate;
    private final Map<VehicleType, Double> rateByType;
    private boolean sealed = false;

    public HourlyPricingStrategy(double defaultHourlyRate) {
        Validator.requirePositive(defaultHourlyRate, "defaultHourlyRate");
        this.defaultHourlyRate = defaultHourlyRate;
        this.rateByType = new EnumMap<>(VehicleType.class);
    }

    public HourlyPricingStrategy withRate(VehicleType vehicleType, double rate) {
        if (sealed) {
            throw new IllegalStateException("Rates cannot be changed after pricing has started.");
        }
        Validator.requireNonNull(vehicleType, "vehicleType");
        Validator.requirePositive(rate, "rate");
        rateByType.put(vehicleType, rate);
        return this;
    }

    @Override
    public double calculatePrice(Ticket ticket) {
        sealed = true;
        if (ticket.getExitTime() == null) {
            throw InvalidTicketException.stillOpen(ticket.getTicketId());
        }
        double rate = rateByType.getOrDefault(ticket.getSpotInfo().getVehicleType(), defaultHourlyRate);
        long durationMillis = ticket.getExitTime() - ticket.getEntryTime();
        long billableHours = Math.max(1, (long) Math.ceil(durationMillis / 3600000.0));
        return billableHours * rate;
    }
}
