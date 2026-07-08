package com.parkingLot.strategy;

import com.parkingLot.exception.PaymentFailedException;

public interface PaymentStrategy {

    boolean pay(double amount) throws PaymentFailedException;
}
