package com.example.shippingPlatform.application;

import com.example.shippingPlatform.domain.*;
import com.example.shippingPlatform.domain.policy.DiscountPolicy;
import com.example.shippingPlatform.infrastructure.model.Product;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class Calculations {

    private final ProductRepository productRepository;
    private final PolicyCatalog discountPolicyCatalog;
    private final Discounts discounts;

    public Calculations(ProductRepository productRepository, PolicyCatalog policyCatalog, Discounts discounts) {
        this.productRepository = productRepository;
        this.discountPolicyCatalog = policyCatalog;
        this.discounts = discounts;
    }

    public BigDecimal calculatePriceFor(ProductId productId, Amount amount, @Nullable PolicyId policyId) {
        Product product = productRepository.findById(productId.value())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        DiscountPolicy discountPolicy = Optional.ofNullable(policyId)
                .map(it -> discountPolicyCatalog.policyFor(policyId.value()))
                .orElse(null);

        return discounts.calculateFinalePriceFor(product, amount, discountPolicy);
    }
}
