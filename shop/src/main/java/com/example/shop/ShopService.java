package com.example.shop;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopService {
    private final Storage storage;

    public ShopService(Storage storage) {
        this.storage = storage;
    }

    public void orderItems(Cart cart) {
        List<String> items = cart.getItems();
        double cost = 0;
        for (String itemName:items) {
            Optional<Item> foundItem = storage.findItemByName(itemName);
            if(foundItem.isPresent()) {
                cost += foundItem.get().getPrice();
            }
        }
        cart.getCustomer().charge(cost);
        System.out.println(cart.getCustomer().getBalance());
    }
}
