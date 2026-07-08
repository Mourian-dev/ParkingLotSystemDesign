package com.parkingLot.strategy;

import com.parkingLot.exception.PaymentFailedException;

public class UPIPaymentStrategy implements PaymentStrategy {
    @Override
    public boolean pay(double amount) throws PaymentFailedException {
        return true; // Simulate successful UPI payment
    }
}
