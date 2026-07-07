package com.parkingLot.strategy;

import com.parkingLot.pojo.Ticket;

public interface PricingStrategy {
    double calculatePrice(Ticket ticket);
}
