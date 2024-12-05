package com.example.shippingPlatform.domain;

import java.util.UUID;

public record ProductId(UUID value) {
    public static ProductId of(String value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }
        return new ProductId(UUID.fromString(value));
    }
}
