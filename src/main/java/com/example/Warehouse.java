package com.example;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Warehouse {
    private final String name;
    private static final Map<String, Warehouse> INSTANCES = new ConcurrentHashMap<>();
    private final List<Product> products = new ArrayList<>();

    private Warehouse(String name) {
        this.name = name;
    }

    public static Warehouse getInstance(String name){
        return INSTANCES.computeIfAbsent(name, Warehouse::new);
    }

    public void addProduct(Product product){
        if(product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        products.add(product);

    }

    public void remove(UUID id){
        products.removeIf(p -> p.uuid().equals(id));
    }

    // is products list empty?
    public boolean isEmpty(){
        return products.isEmpty();
    }

    // clear products
    public void clearProducts(){
        products.clear();
    }

    // get all products (unmodified list)
    public List<Product> getProducts(){
        return Collections.unmodifiableList(products);
    }

    // get product by id
    public Optional<Product> getProductById(UUID id){
        return products.stream()
                .filter(p -> p.uuid()
                .equals(id)).findFirst();

    }

    // update product by id
    public void updateProductPrice(UUID id, BigDecimal newPrice){
        Product product = getProductById(id).orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));
        product.price(newPrice);
    }

    // group products by category
    public Map<Category, List<Product>> getProductsGroupedByCategories(){
        if(products.isEmpty()){
            return Map.of();
        }

        Map<Category, List<Product>> grouped = new HashMap<>();
        for(Product p : products){
            grouped.computeIfAbsent(p.category(), k -> new ArrayList<>()).add(p);
        }
        return grouped;
    }

    // get all expired products
    public List<Perishable> expiredProducts(){
        List<Perishable> expired = new ArrayList<>();
        for(Product p : products){
            if(p instanceof Perishable per && per.isExpired()){
                expired.add(per);
            }
        }
        return expired;
    }

    // get all shippable products
    public List<Shippable> shippableProducts(){
        List<Shippable> shippables = new ArrayList<>();
        for(Product p : products){
            if(p instanceof Shippable ship){
                shippables.add(ship);
            }
        }
        return shippables;
    }

}
