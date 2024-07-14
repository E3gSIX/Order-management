package com.e3gsix.fiap.tech_challenge_4_order_management.service;

import com.e3gsix.fiap.tech_challenge_4_order_management.model.Order;

public interface OrderService {
    public Order create(Order order);

    public Order findById(Long id);
}
