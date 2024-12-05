package com.example.shippingPlatform.domain.policy;

import com.example.shippingPlatform.domain.Amount;

import java.math.BigDecimal;

public record PercentageDiscountPolicy(
        int firstPercentageThreshold,
        BigDecimal firstPercentageBasedDiscount,
        int secondPercentageThreshold,
        BigDecimal secondPercentageBasedDiscount) implements DiscountPolicy {

    @Override
    public BigDecimal calculateDiscount(BigDecimal unitPrice, Amount amount) {
        if (amount.value() >= secondPercentageThreshold) {
            return unitPrice.multiply(secondPercentageBasedDiscount).multiply(BigDecimal.valueOf(amount.value()));
        }
        if (amount.value() >= firstPercentageThreshold) {
            return unitPrice.multiply(firstPercentageBasedDiscount).multiply(BigDecimal.valueOf(amount.value()));
        }
        return BigDecimal.ZERO;
    }

}
