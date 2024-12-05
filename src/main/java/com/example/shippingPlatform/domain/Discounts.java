package com.example.shippingPlatform.domain;

import com.example.shippingPlatform.domain.policy.DefaultPolicy;
import com.example.shippingPlatform.domain.policy.DiscountPolicy;
import com.example.shippingPlatform.infrastructure.model.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class Discounts {
    public BigDecimal calculateFinalePriceFor(Product product, Amount amount, DiscountPolicy discountPolicy) {
        BigDecimal discountValue = Optional.ofNullable(discountPolicy)
                .orElse(new DefaultPolicy())
                .calculateDiscount(product.unitPrice(), amount);

        return product.unitPrice()
                .multiply(BigDecimal.valueOf(amount.value()))
                .subtract(discountValue);
    }
}
