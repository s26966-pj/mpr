package org.example;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderStorage orderStorage;
    private final ProductStorage productStorage;

    OrderService(OrderStorage orderStorage, ProductStorage productStorage) {
        this.orderStorage = orderStorage;
        this.productStorage = productStorage;
    }

    public Order addOrder(int userId, Map<Product, Integer> products, String address) {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Optional<Product> foundProduct = productStorage.getProductByName(entry.getKey().getName());
            if(foundProduct.isEmpty()) {
                return null;
            }
        }
        Order newOrder = new Order(userId, products, address);
        orderStorage.addOrder(newOrder);
        return newOrder;
    }

    public CheckStatusResponse checkStatus(int id) {
        Optional<Order> order = orderStorage.getOrderById(id);
        if(order.isPresent()) {
            return new CheckStatusResponse(order.get().getStatus(), order.get().getProducts());
        }
        return null;
    }

    public 

}
