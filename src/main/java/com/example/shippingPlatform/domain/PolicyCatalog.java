package com.example.shippingPlatform.domain;

import com.example.shippingPlatform.domain.policy.DiscountPolicy;
import com.example.shippingPlatform.domain.policy.PercentageDiscountPolicy;
import com.example.shippingPlatform.domain.policy.QuantityDiscountPolicy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class PolicyCatalog {

    private final Map<UUID, DiscountPolicy> discountPolicies;

    public PolicyCatalog(QuantityDiscountPolicy quantityDiscountPolicy, PercentageDiscountPolicy percentageDiscountPolicy) {
        discountPolicies = new HashMap<>();
        discountPolicies.put(UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d471"), quantityDiscountPolicy);
        discountPolicies.put(UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d472"), percentageDiscountPolicy);
    }

    public DiscountPolicy policyFor(UUID uuid) {
        return discountPolicies.get(uuid);
    }

}
