package com.e3gsix.fiap.tech_challenge_4_order_management.repository;

import com.e3gsix.fiap.tech_challenge_4_order_management.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
