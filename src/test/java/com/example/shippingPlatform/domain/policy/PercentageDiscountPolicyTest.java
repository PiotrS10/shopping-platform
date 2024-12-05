package com.example.shippingPlatform.domain.policy;

import com.example.shippingPlatform.domain.Amount;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PercentageDiscountPolicyTest {

    @ParameterizedTest
    @CsvSource({
            "5, 0",           // Below first quantity threshold
            "10, 100.00",      // Exactly at first quantity threshold
            "25, 500.00"      // Above second quantity threshold
    })
    void testPercentageDiscountPolicy(int amountValue, String expectedDiscount) {
        // Given
        DiscountPolicy policy = percentageDiscountPolicyFor(10, "0.10", 20, "0.20");
        BigDecimal unitePrice = new BigDecimal("100.00");
        Amount amount = new Amount(amountValue);

        // When
        BigDecimal discount = policy.calculateDiscount(unitePrice, amount);

        // Then
        assertEquals(0, discount.compareTo(new BigDecimal(expectedDiscount)),
                "Quantity discount does not match");
    }

    private static DiscountPolicy percentageDiscountPolicyFor(int firstThreshold, String firstDiscount, int secondThreshold, String secondDiscount) {
        return new PercentageDiscountPolicy(firstThreshold, new BigDecimal(firstDiscount), secondThreshold, new BigDecimal(secondDiscount));
    }
}