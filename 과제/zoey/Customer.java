package com.example.shopping;

import java.util.List;
import java.util.Map;

public class Customer {

    private String name;
    private String email;
    private String address;
    private String mobileNumber;
    private ShoppingCart shoppingCart;

    public Customer(String name, String email, String address, String mobileNumber) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.shoppingCart = new ShoppingCart();
    }

    public void addCart(Product product) {
        shoppingCart.add(product);
    }

    public Map<Product, Integer> getShoppingCart() {
        return shoppingCart.getCart();
    }

    public Order order() {
        return new Order(this, shoppingCart.getCart());
    }
}
