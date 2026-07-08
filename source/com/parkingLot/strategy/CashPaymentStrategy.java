package com.parkingLot.strategy;

import com.parkingLot.exception.PaymentFailedException;

public class CashPaymentStrategy implements PaymentStrategy {

    @Override
    public boolean pay(double amount) throws PaymentFailedException {
        return true; // Assuming payment is always successful for this example
    }
}
