package com.example;

import java.math.BigDecimal;
import java.util.UUID;

public class ElectronicsProduct extends Product implements Shippable{
    private final int warrantyMonths;
    private final BigDecimal weight; // kg

    public ElectronicsProduct(UUID uuid, String name, Category category, BigDecimal price, int warrantyMonths, BigDecimal weight) {
        super(uuid, price, category, name);
        if (warrantyMonths < 0) {
            throw new IllegalArgumentException("Warranty months cannot be negative.");
        }
        if (weight == null || weight.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Weight cannot be negative.");
        }
        this.warrantyMonths = warrantyMonths;
        this.weight = weight;
    }

    @Override
    public Double weight() {
        return weight.doubleValue();
    }

    @Override
    public BigDecimal calculateShippingCost() {
        BigDecimal base = BigDecimal.valueOf(79);
        if (weight.compareTo(BigDecimal.valueOf(5.0)) > 0) {
            return base.add(BigDecimal.valueOf(49));
        }
        return base;
    }

    @Override
    public String productDetails() {
        return "Electronics: " + name() + ", Warranty: " + warrantyMonths + " months";
    }
}
