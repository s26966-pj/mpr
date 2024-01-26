package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Order {
    private static final AtomicInteger count = new AtomicInteger(0);
    private final int id;
    private final Map<Product, Integer> products;
    private final int userId;
    private final String address;

    private Status status;

    Order(int userId, Map<Product, Integer> products, String address) {
        id = count.incrementAndGet();
        this.userId = userId;
        this.products = products;
        this.address = address;
        status = Status.NEW;
    }

    public int getId() {
        return id;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public int getUserId() {
        return userId;
    }

    public String getAddress() {
        return address;
    }

    public Status getStatus() {
        return status;
    }
}
