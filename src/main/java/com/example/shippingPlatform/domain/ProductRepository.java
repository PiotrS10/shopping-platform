package com.example.shippingPlatform.domain;

import com.example.shippingPlatform.infrastructure.model.Product;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository {
    Optional<Product> findById(UUID id);
}