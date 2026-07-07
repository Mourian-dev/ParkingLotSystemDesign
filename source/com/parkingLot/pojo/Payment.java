package com.parkingLot.pojo;

import com.parkingLot.util.Validator;

public class Payment {
    private final String paymentId;
    private final double amount;
    private final PaymentMethod paymentMethod;

    private Payment(String paymentId, double amount, PaymentMethod paymentMethod) {
        Validator.requireNonBlank(paymentId, "paymentId");
        Validator.requireNonNegative(amount, "amount");
        Validator.requireNonNull(paymentMethod, "paymentMethod");
        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    public static Payment of(String paymentId, double amount, PaymentMethod paymentMethod) {
        return new Payment(paymentId, amount, paymentMethod);
    }

    public String getPaymentId() {
        return paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
}
