package com.example.shippingPlatform.infrastructure.inbound;


import com.example.shippingPlatform.application.Calculations;
import com.example.shippingPlatform.domain.Amount;
import com.example.shippingPlatform.domain.PolicyId;
import com.example.shippingPlatform.domain.ProductId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

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
        BigDecimal result = request.products().stream()
                .map(product ->
                        calculations.calculatePriceFor(
                                ProductId.of(product.id()),
                                Amount.of(product.amount()),
                                Optional.ofNullable(request.policyId()).map(PolicyId::of).orElse(null)
                        )
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return ResponseEntity.ok(new CalculatedPriceResponse(result));
    }
}
