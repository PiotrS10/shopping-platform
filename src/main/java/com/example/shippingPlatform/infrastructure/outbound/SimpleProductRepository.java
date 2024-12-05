package com.example.shippingPlatform.infrastructure.outbound;


import com.example.shippingPlatform.domain.ProductRepository;
import com.example.shippingPlatform.infrastructure.model.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public class SimpleProductRepository implements ProductRepository {
    private final Map<UUID, Product> productCatalog;

    public SimpleProductRepository() {
        this.productCatalog = new HashMap<>();

        // Adding products with unique UUID
        productCatalog.put(UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479"),
                new Product(UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479"),
                        "Product A", new BigDecimal("100.00")));//10

        productCatalog.put(UUID.fromString("e6342d0e-5457-4977-a6de-4c7f74ef9f2d"),
                new Product(UUID.fromString("e6342d0e-5457-4977-a6de-4c7f74ef9f2d"),
                        "Product B", new BigDecimal("200.00")));//5

        productCatalog.put(UUID.fromString("7e3c6ff1-12ae-4cb2-98e0-d42a4f60617f"),
                new Product(UUID.fromString("7e3c6ff1-12ae-4cb2-98e0-d42a4f60617f"),
                        "Product C", new BigDecimal("300.00")));//9
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return Optional.ofNullable(productCatalog.get(id));
    }
}
