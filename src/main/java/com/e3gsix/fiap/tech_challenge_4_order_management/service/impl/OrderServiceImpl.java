package com.e3gsix.fiap.tech_challenge_4_order_management.service.impl;


import com.e3gsix.fiap.tech_challenge_4_order_management.exceptions.NotFoundException;
import com.e3gsix.fiap.tech_challenge_4_order_management.model.Order;
import com.e3gsix.fiap.tech_challenge_4_order_management.repository.OrderRepository;
import com.e3gsix.fiap.tech_challenge_4_order_management.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Transactional
    public Order create(Order order) {
        order.validate();
        return this.orderRepository.save(order);
    }

    public Order findById(Long id) {
        validateExistence(id);
        return this.orderRepository.findById(id).orElseThrow(() -> createNotFoundOrderException(id));
    }

    private void validateExistence(Long id) {
        if (!this.orderRepository.existsById(id)) throw createNotFoundOrderException(id);
    }

    private NotFoundException createNotFoundOrderException(Long id){
        return new NotFoundException("Order with ID " + id + " not found.");
    }
}