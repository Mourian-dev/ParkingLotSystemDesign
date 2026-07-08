package com.parkingLot.observer;

public interface PaymentEventListener {
    void onEvent(PaymentEvent event);
}
