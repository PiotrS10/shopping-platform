package com.example.shippingPlatform.domain.policy;

import com.example.shippingPlatform.domain.Amount;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultPolicyTest {

    @ParameterizedTest
    @CsvSource({
            "5, 0",     // Below first quantity threshold
            "10, 0",    // Exactly at first quantity threshold
            "25, 0"     // Above second quantity threshold
    })
    void testDefaultDiscountPolicy(int amountValue, String expectedDiscount) {
        // Given
        DiscountPolicy policy = new DefaultPolicy();
        BigDecimal unitePrice = new BigDecimal("100.00");
        Amount amount = new Amount(amountValue);

        // When
        BigDecimal discount = policy.calculateDiscount(unitePrice, amount);

        // Then
        assertEquals(0, discount.compareTo(new BigDecimal(expectedDiscount)),
                "Quantity discount does not match");
    }
}
