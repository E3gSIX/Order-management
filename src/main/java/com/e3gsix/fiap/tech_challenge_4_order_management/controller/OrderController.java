package com.e3gsix.fiap.tech_challenge_4_order_management.controller;

import com.e3gsix.fiap.tech_challenge_4_order_management.controller.intefaces.IOrderController;
import com.e3gsix.fiap.tech_challenge_4_order_management.model.Order;
import com.e3gsix.fiap.tech_challenge_4_order_management.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController implements IOrderController {
    private final OrderService orderService;

    @PostMapping()
    @Override
    public ResponseEntity<Order> create(@RequestBody Order order, UriComponentsBuilder uriComponentsBuilder) {
        Order createdOrder = this.orderService.create(order);

        var uri = uriComponentsBuilder.path("/orders/{orderId}").buildAndExpand(order.getId()).toUri();

        return ResponseEntity.created(uri).body(createdOrder);
    }

    @GetMapping("/{orderId}")
    @Override
    public ResponseEntity<Order> findById(@PathVariable Long orderId) {
        Order findedOrder = this.orderService.findById(orderId);

        return ResponseEntity.ok(findedOrder);
    }
}
