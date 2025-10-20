package com.example;

import java.math.BigDecimal;
import java.util.UUID;

public abstract class Product {
    private final UUID uuid;
    private final String name;
    private final Category category;
    private BigDecimal price;

    public Product(UUID uuid, BigDecimal price, Category category, String name) {
        if (uuid == null) throw new IllegalArgumentException("UUID cannot be null.");
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Product name cannot be blank.");
        if (category == null) throw new IllegalArgumentException("Category cannot be null.");
        if (price == null) throw new IllegalArgumentException("Price cannot be negative.");

        this.uuid = uuid;
        this.price = price;
        this.category = category;
        this.name = name;
    }

    public UUID uuid() { return uuid; }
    public String name() { return name; }
    public Category category() { return category; }
    public BigDecimal price() { return price; }

    public void price(BigDecimal newPrice){
        if(newPrice == null || newPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.price = newPrice;
    }

    public abstract String productDetails();
}
