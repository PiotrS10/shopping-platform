package com.example.shippingPlatform.infrastructure.inbound;

import java.util.List;

public record CalculatedPriceRequest(List<Product> products, String policyId) {
}
