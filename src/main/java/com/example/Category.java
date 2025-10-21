package com.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Category {
    private final String name;
    private static final Map<String, Category> CACHE = new ConcurrentHashMap<>();

    private Category(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public static Category of(String name){
        if(name == null){
            throw new IllegalArgumentException("Category name can't be null");
        }
        String trimmedName = name.trim();
        if (trimmedName.isEmpty()) {
            throw new IllegalArgumentException("Category name can't be blank");
        }

        String normalizedName = trimmedName.substring(0, 1).toUpperCase() + trimmedName.substring(1).toLowerCase();

        // if already exists return that, if not, create a new.
        return CACHE.computeIfAbsent(normalizedName, Category::new);

    }
}
