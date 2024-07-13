package com.e3gsix.fiap.tech_challenge_4_order_management.model;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class OrderTest {

    @Test
    void orderModel_NoArgsConstructor_NullGetters() {
        Order order = new Order();

        assertNull(order.getId());
        assertNull(order.getCustomerId());
        assertNull(order.getItems());
    }

    @Test
    void orderModel_AllArgsConstructor_ValuedGetters() {
        Long expectedOrderId = 1L;
        Long expectedCustomerId = 10L;

        List<Item> expectedItems = List.of(
                new Item(1L, 1L, BigInteger.TEN),
                new Item(2L, 1L, BigInteger.valueOf(5))
        );

        Order order = new Order(expectedOrderId, expectedCustomerId, expectedItems);

        assertEquals(expectedOrderId, order.getId());
        assertEquals(expectedCustomerId, order.getCustomerId());

        for (int i = 0; i < order.getItems().size(); i++) {
            assertEquals(expectedItems.get(i), order.getItems().get(i));
        }
    }
}