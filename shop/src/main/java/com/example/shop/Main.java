package com.example.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
	private final ShopService shopService;

	public Main(ShopService shopService) {
		this.shopService = shopService;
		execProcess();
	}

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	public void execProcess() {
		Customer customer = new Customer(1, 100);
		Cart cart = new Cart(customer);

		cart.addItem("milk");
		cart.addItem("beer");
		shopService.orderItems(cart);
	}
}
