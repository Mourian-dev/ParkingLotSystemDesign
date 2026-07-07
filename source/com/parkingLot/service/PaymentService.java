package com.parkingLot.service;

import com.parkingLot.pojo.Payment;
import com.parkingLot.pojo.PaymentMethod;

import java.util.UUID;

public class PaymentService {

    public Payment collect(double amount, PaymentMethod paymentMethod) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
        if (paymentMethod == null) {
            throw new IllegalArgumentException("Payment method is required.");
        }
        return new Payment(UUID.randomUUID().toString(), amount, paymentMethod);
    }
}

