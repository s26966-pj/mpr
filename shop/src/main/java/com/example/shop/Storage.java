package com.example.shop;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class Storage {
    private final List<Item> items = List.of(
            new Item("milk", 30),
            new Item("beer", 20)
    );

    public List<Item> getItems() {
        return items;
    }

    public Optional<Item> findItemByName(String name) {
       return items.stream().filter(i -> i.getName().equals(name)).findFirst();
    }
}
