package com.example.shippingPlatform.infrastructure.inbound;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CalculationControllerEndToEndTest {

    @LocalServerPort
    private int port;

    @Test
    @DirtiesContext
    void calculatePriceBasedOnQuantityPolicyTest() {
        String url = "http://localhost:" + port + "/api/calculate";

        Product product1 = new Product("f47ac10b-58cc-4372-a567-0e02b2c3d479", 10); // Product A based price 100
        Product product2 = new Product("e6342d0e-5457-4977-a6de-4c7f74ef9f2d", 5);  // Product B based price 200

        CalculatedPriceRequest request = new CalculatedPriceRequest(
                List.of(product1, product2),
                "f47ac10b-58cc-4372-a567-0e02b2c3d471" // UUID for quantity discount policy
        );

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CalculatedPriceResponse> response = restTemplate.postForEntity(url, request, CalculatedPriceResponse.class);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(0, new BigDecimal("1995.00").compareTo(new BigDecimal(String.valueOf(response.getBody().finalePrice()))),
                "Quantity discount does not match");
    }

    @Test
    @DirtiesContext
    void calculatePriceBasedOnPercentagePolicyTest() {
        String url = "http://localhost:" + port + "/api/calculate";

        Product product1 = new Product("f47ac10b-58cc-4372-a567-0e02b2c3d479", 10); // Product A based price 100
        Product product2 = new Product("e6342d0e-5457-4977-a6de-4c7f74ef9f2d", 5);  // Product B based price 200

        CalculatedPriceRequest request = new CalculatedPriceRequest(
                List.of(product1, product2),
                "f47ac10b-58cc-4372-a567-0e02b2c3d472" // UUID for quantity discount policy
        );

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CalculatedPriceResponse> response = restTemplate.postForEntity(url, request, CalculatedPriceResponse.class);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(0, new BigDecimal("1900.00").compareTo(new BigDecimal(String.valueOf(response.getBody().finalePrice()))),
                "Quantity discount does not match");
    }
}