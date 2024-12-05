package com.example.shippingPlatform.infrastructure.inbound;


import com.example.shippingPlatform.application.Calculations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/calculate")
public class CalculationController {
    private final Calculations calculations;

    @Autowired
    public CalculationController(Calculations calculations) {
        this.calculations = calculations;
    }

    @PostMapping
    public ResponseEntity<CalculatedPriceResponse> calculatePrice(@RequestBody CalculatedPriceRequest request) {
        BigDecimal result = new BigDecimal("1");

        return ResponseEntity.ok(new CalculatedPriceResponse(result));
    }
}
