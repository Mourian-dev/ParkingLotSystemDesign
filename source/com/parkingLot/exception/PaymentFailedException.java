package com.parkingLot.exception;

import com.parkingLot.pojo.PaymentMethod;

public class PaymentFailedException extends ParkingException {

    private PaymentFailedException(String message) {
        super(message);
    }

    public static PaymentFailedException forMethod(PaymentMethod method) {
        return new PaymentFailedException("Payment processing failed for method: " + method);
    }
}
