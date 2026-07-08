package com.parkingLot.observer;

import com.parkingLot.pojo.Payment;
import com.parkingLot.util.Validator;

public class PaymentEvent {
    private final PaymentEventType type;
    private final Payment payment;
    private final long timestamp;

    private PaymentEvent(PaymentEventType type, Payment payment, long timestamp) {
        Validator.requireNonNull(type, "Payment Type");
        Validator.requireNonNull(payment, "Payment");
        this.type = type;
        this.payment = payment;
        this.timestamp = timestamp;
    }

    public static PaymentEvent of(PaymentEventType type, Payment payment) {
        return new PaymentEvent(type, payment, System.currentTimeMillis());
    }

    public PaymentEventType getType() {
        return type;
    }

    public Payment getPayment() {
        return payment;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
