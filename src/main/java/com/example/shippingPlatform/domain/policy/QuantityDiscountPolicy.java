package com.example.shippingPlatform.domain.policy;

import com.example.shippingPlatform.domain.Amount;

import java.math.BigDecimal;

public record QuantityDiscountPolicy(
        int firstQuantityThreshold,
        BigDecimal firstAmountBasedDiscount,
        int secondQuantityThreshold,
        BigDecimal secondAmountBasedDiscount) implements DiscountPolicy {

    @Override
    public BigDecimal calculateDiscount(BigDecimal unitPrice, Amount amount) {
        if (amount.value() >= secondQuantityThreshold) {
            return secondAmountBasedDiscount;
        }
        if (amount.value() >= firstQuantityThreshold) {
            return firstAmountBasedDiscount;
        }
        return BigDecimal.ZERO;
    }
}
