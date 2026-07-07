package com.parkingLot.pojo;

public class Payment {
    private final String paymentId;
    private final double amount;
    private final PaymentMethod paymentMethod;

    public Payment(String paymentId, double amount, PaymentMethod paymentMethod) {
        if (paymentId == null || paymentId.isBlank()) {
            throw new IllegalArgumentException("Payment id is required.");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
        if (paymentMethod == null) {
            throw new IllegalArgumentException("Payment method is required.");
        }
        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
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
