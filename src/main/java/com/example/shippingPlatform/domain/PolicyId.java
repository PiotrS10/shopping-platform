package com.example.shippingPlatform.domain;

import java.util.UUID;

public record PolicyId(UUID value) {
    public static PolicyId of(String value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }
        return new PolicyId(UUID.fromString(value));
    }
}
