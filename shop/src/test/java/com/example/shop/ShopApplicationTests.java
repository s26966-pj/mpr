package com.example.shop;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class ShopApplicationTests {
	@MockBean
	private Storage storage;
	@Autowired
	private ShopService  shopService;

	@Test
	void shouldOrderItems() {
		Customer customer = new Customer(1,100);
		Cart cart = new Cart(customer);

		cart.addItem("milk");
		when(storage.findItemByName(anyString())).thenReturn(Optional.of(new Item("milk", 30)));

		shopService.orderItems(cart);

		assertThat(customer.getBalance()).isEqualTo(70);
	}

}
