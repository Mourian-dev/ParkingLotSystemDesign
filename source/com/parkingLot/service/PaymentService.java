package com.parkingLot.service;

import com.parkingLot.pojo.Payment;
import com.parkingLot.pojo.PaymentMethod;
import com.parkingLot.util.Validator;

import java.util.UUID;

public class PaymentService {

    public Payment collect(double amount, PaymentMethod paymentMethod) {
        Validator.requireNonNegative(amount, "amount");
        Validator.requireNonNull(paymentMethod, "paymentMethod");
        return Payment.of(UUID.randomUUID().toString(), amount, paymentMethod);
    }
}
