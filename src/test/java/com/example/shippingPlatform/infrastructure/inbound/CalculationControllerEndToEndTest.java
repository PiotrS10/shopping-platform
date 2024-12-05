package com.example.shippingPlatform.infrastructure.inbound;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CalculationControllerEndToEndTest {

    RestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplate = new RestTemplate();
    }

    @Test
    void calculatePriceBasedOnQuantityPolicyTest() {
        // given
        CalculatedPriceRequest request = new CalculatedPriceRequest(
                List.of(productLineFor10ProductA(), productLineFor5ProductB()),
                quantityDiscountPolicyUUID());

        // when
        ResponseEntity<CalculatedPriceResponse> response = restTemplate.postForEntity(getUrl(), request, CalculatedPriceResponse.class);

        // then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(0, new BigDecimal("1995.00").compareTo(new BigDecimal(String.valueOf(response.getBody().finalePrice()))),
                "Quantity discount does not match");
    }

    @Test
    void calculatePriceBasedOnPercentagePolicyTest() {
        // given
        CalculatedPriceRequest request = new CalculatedPriceRequest(
                List.of(productLineFor10ProductA(), productLineFor5ProductB()),
                percentageDiscountPolicyUUID());

        // when
        ResponseEntity<CalculatedPriceResponse> response = restTemplate.postForEntity(getUrl(), request, CalculatedPriceResponse.class);

        // then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(0, new BigDecimal("1900.00").compareTo(new BigDecimal(String.valueOf(response.getBody().finalePrice()))),
                "Quantity discount does not match");
    }

    private static String percentageDiscountPolicyUUID() {
        return "f47ac10b-58cc-4372-a567-0e02b2c3d472";
    }

    private static String quantityDiscountPolicyUUID() {
        return "f47ac10b-58cc-4372-a567-0e02b2c3d471";
    }

    private static ProductLine productLineFor5ProductB() {
        return new ProductLine("e6342d0e-5457-4977-a6de-4c7f74ef9f2d", 5);
    }

    private static ProductLine productLineFor10ProductA() {
        return new ProductLine("f47ac10b-58cc-4372-a567-0e02b2c3d479", 10);
    }

    private String getUrl() {
        return "http://localhost:" + port + "/api/calculate";
    }
}