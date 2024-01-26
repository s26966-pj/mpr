package org.example;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductStorage {
    private final List<Product> products;

    ProductStorage(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public Optional<Product> getProductByName(String name) {
        return products.stream().filter(product -> product.getName().equals(name)).findFirst();
    }

}
