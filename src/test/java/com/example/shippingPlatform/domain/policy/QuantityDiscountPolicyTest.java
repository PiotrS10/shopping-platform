package com.example.shippingPlatform.domain.policy;

import com.example.shippingPlatform.domain.Amount;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuantityDiscountPolicyTest {

    @ParameterizedTest
    @CsvSource({
            "5, 0",           // Below first threshold
            "10, 50.00",      // Exactly at first threshold
            "25, 100.00"      // Above second threshold
    })
    void testQuantityDiscountPolicy(int amountValue, String expectedDiscount) {
        // Given
        DiscountPolicy policy = quantityDiscountPolicyFor(10, "50", 20, "100");
        BigDecimal unitPrice = BigDecimal.valueOf(100);
        Amount amount = new Amount(amountValue);

        // When
        BigDecimal discount = policy.calculateDiscount(unitPrice, amount);

        // Then
        assertEquals(0, new BigDecimal(expectedDiscount).compareTo(discount), "values are not equal");
    }

    private static DiscountPolicy quantityDiscountPolicyFor(int firstThreshold, String firstDiscount, int secondThreshold, String secondDiscount) {
        return new QuantityDiscountPolicy(firstThreshold, new BigDecimal(firstDiscount), secondThreshold, new BigDecimal(secondDiscount));
    }
}