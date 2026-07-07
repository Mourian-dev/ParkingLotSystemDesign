package com.parkingLot.pojo;

public class Payment {
    private final String paymentId;
    private final double amount;
    private final PaymentMethod paymentMethod;

    public Payment(String paymentId, double amount, PaymentMethod paymentMethod) {
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
