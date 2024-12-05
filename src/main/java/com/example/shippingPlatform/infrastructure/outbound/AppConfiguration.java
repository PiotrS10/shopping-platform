package com.example.shippingPlatform.infrastructure.outbound;


import com.example.shippingPlatform.domain.policy.PercentageDiscountPolicy;
import com.example.shippingPlatform.domain.policy.QuantityDiscountPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class AppConfiguration {

    @Bean
    public QuantityDiscountPolicy quantityDiscountPolicy(
            @Value("${shop2.discount-policies.quantity.firstQuantityThreshold}") int firstQuantityThreshold,
            @Value("${shop2.discount-policies.quantity.firstAmountBasedDiscount}") double firstAmountBasedDiscount,
            @Value("${shop2.discount-policies.quantity.secondQuantityThreshold}") int secondQuantityThreshold,
            @Value("${shop2.discount-policies.quantity.secondAmountBasedDiscount}") double secondAmountBasedDiscount
    ) {
        BigDecimal discount1BigDecimal = BigDecimal.valueOf(firstAmountBasedDiscount);
        BigDecimal discount2BigDecimal = BigDecimal.valueOf(secondAmountBasedDiscount);

        return new QuantityDiscountPolicy(firstQuantityThreshold, discount1BigDecimal, secondQuantityThreshold, discount2BigDecimal);
    }

    @Bean
    public PercentageDiscountPolicy percentageDiscountPolicy(
            @Value("${shop2.discount-policies.percentage.firstPercentageThreshold}") int firstPercentageThreshold,
            @Value("${shop2.discount-policies.percentage.firstPercentageBasedDiscount}") double firstPercentageBasedDiscount,
            @Value("${shop2.discount-policies.percentage.secondPercentageThreshold}") int secondPercentageThreshold,
            @Value("${shop2.discount-policies.percentage.secondPercentageBasedDiscount}") double secondPercentageBasedDiscount
    ) {
        BigDecimal discount1BigDecimal = BigDecimal.valueOf(firstPercentageBasedDiscount);
        BigDecimal discount2BigDecimal = BigDecimal.valueOf(secondPercentageBasedDiscount);

        return new PercentageDiscountPolicy(firstPercentageThreshold, discount1BigDecimal, secondPercentageThreshold, discount2BigDecimal);
    }
}
