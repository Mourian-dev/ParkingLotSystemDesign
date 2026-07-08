package com.parkingLot.repository;

import com.parkingLot.pojo.Payment;
import com.parkingLot.util.Validator;

import java.util.Map;

public interface PaymentRepository {

    static PaymentRepository getInstance(Map<String, Payment> paymentStore) {
        Validator.requireNonNull(paymentStore, "paymentStore");
        return InMemoryPaymentRepository.getInstance(paymentStore);
    }

    void save(Payment payment);
    Payment getPayment(String paymentId);
}
