package com.example.shippingPlatform.infrastructure.inbound;


import com.example.shippingPlatform.application.Calculations;
import com.example.shippingPlatform.domain.Amount;
import com.example.shippingPlatform.domain.PolicyId;
import com.example.shippingPlatform.domain.ProductId;
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

    public CalculationController(Calculations calculations) {
        this.calculations = calculations;
    }

    @PostMapping
    public ResponseEntity<CalculatedPriceResponse> calculatePrice(@RequestBody CalculatedPriceRequest request) {
        PolicyId policyId = Optional.ofNullable(request.policyId()).map(PolicyId::of).orElse(null);

        BigDecimal result = request.productLines().stream()
                .map(product ->
                        calculations.calculatePriceFor(
                                ProductId.of(product.productId()),
                                Amount.of(product.amount()),
                                policyId
                        )
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return ResponseEntity.ok(new CalculatedPriceResponse(result));
    }
}
