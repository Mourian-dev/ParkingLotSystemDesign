package com.parkingLot.factory;

import com.parkingLot.pojo.PaymentMethod;
import com.parkingLot.strategy.PaymentStrategy;
import com.parkingLot.strategy.CashPaymentStrategy;
import com.parkingLot.strategy.UPIPaymentStrategy;
import com.parkingLot.util.Validator;

import java.util.Map;

public class PaymentStrategyFactory {
    private static final Map<PaymentMethod, PaymentStrategy> strategies = Map.of(
            PaymentMethod.CASH, new CashPaymentStrategy(),
            PaymentMethod.CARD, new CashPaymentStrategy(),
            PaymentMethod.UPI, new UPIPaymentStrategy()
    );

    public static PaymentStrategy getStrategy(PaymentMethod paymentMethod) {
        Validator.requireNonNull(paymentMethod, "paymentMethod");
        PaymentStrategy strategy = strategies.get(paymentMethod);
        if (strategy == null) {
            throw new IllegalArgumentException("Unknown payment method: " + paymentMethod);
        }
        return strategy;
    }
}
