package com.example.shippingPlatform.infrastructure.model;

import java.math.BigDecimal;
import java.util.UUID;

public record Product(UUID id, String name, BigDecimal unitPrice) {
}
