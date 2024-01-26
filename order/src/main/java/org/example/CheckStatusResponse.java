package org.example;

import java.util.List;
import java.util.Map;

public class CheckStatusResponse {
    private final Status status;
    private final Map<Product, Integer> products;
    CheckStatusResponse(Status status, Map<Product, Integer> products) {
        this.products = products;
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }
}
