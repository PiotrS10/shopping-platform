package com.example.shippingPlatform.domain.policy;


import com.example.shippingPlatform.domain.Amount;

import java.math.BigDecimal;

public interface DiscountPolicy {
    BigDecimal calculateDiscount(BigDecimal unitPrice, Amount amount);
}