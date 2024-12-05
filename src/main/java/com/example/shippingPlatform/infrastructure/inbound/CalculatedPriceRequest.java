package com.example.shippingPlatform.infrastructure.inbound;

import java.util.List;

public record CalculatedPriceRequest(List<ProductLine> productLines, String policyId) {
}
