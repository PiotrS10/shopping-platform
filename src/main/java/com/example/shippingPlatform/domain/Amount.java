package com.example.shippingPlatform.domain;

public record Amount(int value) {
    public Amount {
        if (value < 1 || value > 250) {
            throw new IllegalArgumentException("Amount must be between 1 and 250");
        }
    }

    public static Amount of(int value) {
        return new Amount(value);
    }
}
