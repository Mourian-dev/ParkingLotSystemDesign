package com.parkingLot.repository;

import com.parkingLot.pojo.Payment;
import com.parkingLot.util.Validator;

import java.util.Map;

public class InMemoryPaymentRepository implements PaymentRepository {
    private static InMemoryPaymentRepository instance;

    private final Map<String, Payment> paymentStore;

    private InMemoryPaymentRepository(Map<String, Payment> paymentStore) {
        Validator.requireNonNull(paymentStore, "paymentStore");
        this.paymentStore = paymentStore;
    }

    static PaymentRepository getInstance(Map<String, Payment> paymentStore) {
        Validator.requireNonNull(paymentStore, "paymentStore");
        if (instance == null) {
            instance = new InMemoryPaymentRepository(paymentStore);
        }
        return instance;
    }

    @Override
    public void save(Payment payment) {
        Validator.requireNonNull(payment, "payment");
        paymentStore.put(payment.getPaymentId(), payment);
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentStore.get(paymentId);
    }
}
