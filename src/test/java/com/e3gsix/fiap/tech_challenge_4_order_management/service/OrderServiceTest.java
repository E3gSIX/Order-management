package com.e3gsix.fiap.tech_challenge_4_order_management.service;

import com.e3gsix.fiap.tech_challenge_4_order_management.controller.exception.NotFoundException;
import com.e3gsix.fiap.tech_challenge_4_order_management.model.Item;
import com.e3gsix.fiap.tech_challenge_4_order_management.model.Order;
import com.e3gsix.fiap.tech_challenge_4_order_management.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void create_ValidValues_ShouldCallSave() {
        Order order = new Order(
                null,
                10L,
                List.of(new Item(1L, "6694014dd2dfcd550fcfdc07", BigInteger.TEN))
        );

        this.orderService.create(order);

        verify(orderRepository).save(order);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void create_NotValidValues_ShouldThrowUnsupportedOperationException() {
        Order order = new Order(null, null, null);
        this.orderService.create(order);
    }

    @Test(expected = NotFoundException.class)
    public void findById_NotExistentOrder_ShouldThrowNotFoundException() {
        this.orderService.findById(1L);
    }

    @Test
    public void findById_ExistentOrder_ShouldReturnOrder() {
        Long mockOrderId = 1L;
        Order expectedOrder = new Order(mockOrderId, 10L, new ArrayList<>());

        when(orderRepository.existsById(mockOrderId)).thenReturn(true);
        when(orderRepository.findById(mockOrderId)).thenReturn(Optional.of(expectedOrder));

        Order result = this.orderService.findById(mockOrderId);

        assertEquals(expectedOrder, result);
    }
}