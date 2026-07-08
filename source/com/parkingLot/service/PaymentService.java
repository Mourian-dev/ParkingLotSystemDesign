package com.parkingLot.service;

import com.parkingLot.exception.PaymentFailedException;
import com.parkingLot.factory.PaymentStrategyFactory;
import com.parkingLot.observer.PaymentEvent;
import com.parkingLot.observer.PaymentEventListener;
import com.parkingLot.observer.PaymentEventType;
import com.parkingLot.pojo.Payment;
import com.parkingLot.pojo.PaymentMethod;
import com.parkingLot.repository.PaymentRepository;
import com.parkingLot.strategy.PaymentStrategy;
import com.parkingLot.util.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PaymentService {
    private final List<PaymentEventListener> listeners = new ArrayList<>();

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        Validator.requireNonNull(paymentRepository, "paymentRepository");
        this.paymentRepository = paymentRepository;
    }

    public Payment collect(double amount, PaymentMethod paymentMethod) {
        Validator.requireNonNegative(amount, "amount");
        Validator.requireNonNull(paymentMethod, "paymentMethod");

        PaymentStrategy strategy = PaymentStrategyFactory.getStrategy(paymentMethod);
        Payment payment = Payment.of(UUID.randomUUID().toString(), amount, paymentMethod);

        publish(payment, PaymentEventType.PAYMENT_INITIATED);
        try{
            strategy.pay(amount);
            publish(payment, PaymentEventType.PAYMENT_SUCCESSFUL);
            paymentRepository.save(payment);
        } catch (PaymentFailedException e) {
            publish(payment, PaymentEventType.PAYMENT_FAILED);
            throw e;
        }

        return payment;
    }

    public void registerListeners(PaymentEventListener listener) {
        Validator.requireNonNull(listener, "listener");
        listeners.add(listener);
    }

    private void publish(Payment payment, PaymentEventType type) {
        PaymentEvent event = PaymentEvent.of(type, payment);
        listeners.stream().forEach(l -> l.onEvent(event));
    }

}
