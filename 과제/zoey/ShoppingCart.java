package com.example.shopping;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private final Map<Product, Integer> products;

    public ShoppingCart() {
        this.products = new HashMap<>();
    }

    public void add(Product product) {
        product.validateStockAvailability();
        this.products.put(product, products.getOrDefault(product, 0) + 1);
    }

    public Map<Product, Integer> getCart() {
        return Map.copyOf(products);
    }

    public void showList() {
        products.forEach((key, value) -> System.out.println(key + "[" + value + "]"));
    }
}
