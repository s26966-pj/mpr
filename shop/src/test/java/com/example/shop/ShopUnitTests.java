package com.example.shop;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShopUnitTests {
    @Mock
    private Storage storage;
    @InjectMocks
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
