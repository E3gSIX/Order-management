package com.e3gsix.fiap.tech_challenge_4_order_management.model;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
public class OrderTest {

    @Test
    public void orderModel_NoArgsConstructor_NullGetters() {
        Order order = new Order();

        assertNull(order.getId());
        assertNull(order.getCustomerId());
        assertNull(order.getItems());
    }

    @Test
    public void orderModel_AllArgsConstructor_ValuedGetters() {
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
    @Test(expected = UnsupportedOperationException.class)
    public void validate_NotValidCustomerId_ShouldThrowUnsupportedOperationException() {
        Order order = new Order(
                null,
                null,
                List.of(new Item(1L, 1L, BigInteger.TEN))
        );

        order.validate();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void validate_NotValidItemsList_ShouldThrowUnsupportedOperationException() {
        Order order = new Order(null, null, null);
        order.validate();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void validate_NotValidZeroQuantities_ShouldThrowUnsupportedOperationException() {
        Order order = new Order(
                null,
                null,
                List.of(new Item(1L, 1L, BigInteger.ZERO))
        );

        order.validate();
    }
}