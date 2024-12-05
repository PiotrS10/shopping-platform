package com.example.shippingPlatform.domain.policy;

import com.example.shippingPlatform.domain.Amount;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountPoliciesTest {

    @ParameterizedTest
    @CsvSource({
            "5, 0",           // Below first threshold
            "10, 50.00",      // Exactly at first threshold
            "25, 100.00"      // Above second threshold
    })
    void testQuantityDiscountPolicy(int amount, String expectedDiscount) {
        // Given
        QuantityDiscountPolicy policy = new QuantityDiscountPolicy(10, new BigDecimal("50.00"), 20, new BigDecimal("100.00"));

        // When
        BigDecimal discount = policy.calculateDiscount(new BigDecimal("100.00"), new Amount(amount));

        // Then
        assertEquals(new BigDecimal(expectedDiscount), discount);
    }

    @ParameterizedTest
    @CsvSource({
            "5, 0",           // Below first quantity threshold
            "10, 100.00",      // Exactly at first quantity threshold
            "25, 500.00"      // Above second quantity threshold
    })
    void testPercentageDiscountPolicy(int amount, String expectedDiscount) {
        // Given
        PercentageDiscountPolicy policy = new PercentageDiscountPolicy(10, new BigDecimal("0.10"), 20, new BigDecimal("0.20"));

        // When
        BigDecimal discount = policy.calculateDiscount(new BigDecimal("100.00"), new Amount(amount));

        // Then
        assertEquals(0, discount.compareTo(new BigDecimal(expectedDiscount)),
                "Quantity discount does not match");
    }

    static Stream<TestData> provideTestDataForPolicies() {
        return Stream.of(
                new TestData(5, "0", "0"),             // Below thresholds
                new TestData(10, "50.00", "100.00"),    // Exactly at first threshold
                new TestData(25, "100.00", "500.00")   // Above second threshold
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestDataForPolicies")
    void testBothPolicies(TestData testData) {
        // Given
        QuantityDiscountPolicy quantityPolicy = new QuantityDiscountPolicy(10, new BigDecimal("50.00"), 20, new BigDecimal("100.00"));
        PercentageDiscountPolicy percentagePolicy = new PercentageDiscountPolicy(10, new BigDecimal("0.10"), 20, new BigDecimal("0.20"));

        // When
        BigDecimal quantityDiscount = quantityPolicy.calculateDiscount(new BigDecimal("100.00"), new Amount(testData.amount));
        BigDecimal percentageDiscount = percentagePolicy.calculateDiscount(new BigDecimal("100.00"), new Amount(testData.amount));

        // Then
        assertEquals(0, quantityDiscount.compareTo(new BigDecimal(testData.expectedQuantityDiscount)),
                "Quantity discount does not match");
        assertEquals(0, percentageDiscount.compareTo(new BigDecimal(testData.expectedPercentageDiscount)),
                "Percentage discount does not match");
    }

    private record TestData(int amount, String expectedQuantityDiscount, String expectedPercentageDiscount) {
    }
}