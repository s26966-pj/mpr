package com.example.shop;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final Customer customer;
    private final List<String> items = new ArrayList<>();

    public Cart(Customer customer) {
        this.customer = customer;
    }

    public List<String> getItems() {
        return items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void addItem(String item) {
        items.add(item);
    }
}
