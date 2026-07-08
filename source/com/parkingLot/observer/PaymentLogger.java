package com.parkingLot.observer;

import com.parkingLot.util.Validator;

public class PaymentLogger implements PaymentEventListener {

    @Override
    public void onEvent(PaymentEvent event) {
        Validator.requireNonNull(event, "event");
        if (event.getPayment() != null) {
            System.out.printf("[%s] Payment=%s | Amount=%.2f | Method=%s%n",
                    event.getType(),
                    event.getPayment().getPaymentId(),
                    event.getPayment().getAmount(),
                    event.getPayment().getPaymentMethod());
        } else {
            System.out.printf("[%s] Payment processing initiated%n", event.getType());
        }
    }
}
