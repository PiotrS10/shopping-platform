package com.example.shippingPlatform.domain.policy;

import com.example.shippingPlatform.domain.Amount;

import java.math.BigDecimal;

public class DefaultPolicy implements DiscountPolicy {

    @Override
    public BigDecimal calculateDiscount(BigDecimal unitPrice, Amount amount) {
        return BigDecimal.ZERO;
    }
}
