package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class Main {
    private final OrderStorage orderStorage;
    private final ProductStorage productStorage;
    private final OrderService orderService;
    Main(OrderStorage orderStorage, ProductStorage productStorage, OrderService orderService) {
        this.orderStorage = orderStorage;
        this.productStorage = productStorage;
        this.orderService = orderService;
        execProcess();
    }
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    public void execProcess() {
        Product p1 = new Product("p1");
        Product p2 = new Product("p2");


        orderService.addOrder(1, Map.of(p1, 1), "123");
        System.out.println(orderStorage.getOrderById(1).get().getProducts().values());

        ;
    };
}