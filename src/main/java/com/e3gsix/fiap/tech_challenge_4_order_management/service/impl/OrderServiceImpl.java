package com.e3gsix.fiap.tech_challenge_4_order_management.service.impl;

import com.e3gsix.fiap.tech_challenge_4_order_management.clients.CustomerClient;
import com.e3gsix.fiap.tech_challenge_4_order_management.clients.ProductClient;
import com.e3gsix.fiap.tech_challenge_4_order_management.controller.exception.NotFoundException;
import com.e3gsix.fiap.tech_challenge_4_order_management.dto.ProductFindByIdResponseDTO;
import com.e3gsix.fiap.tech_challenge_4_order_management.dto.StockActionEnum;
import com.e3gsix.fiap.tech_challenge_4_order_management.dto.StockProductUpdateRequestDTO;
import com.e3gsix.fiap.tech_challenge_4_order_management.gateway.OrderMessagingGateway;
import com.e3gsix.fiap.tech_challenge_4_order_management.model.Item;
import com.e3gsix.fiap.tech_challenge_4_order_management.model.Order;
import com.e3gsix.fiap.tech_challenge_4_order_management.repository.OrderRepository;
import com.e3gsix.fiap.tech_challenge_4_order_management.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMessagingGateway orderMessagingGateway;

    @Transactional
    public Order create(Order order) {
        order.validate();

        try {
            validateCustomer(order.getCustomerId());
            validateEnoughStock(order.getItems());

            manageInStockProducts(order.getItems());

            return this.orderRepository.save(order);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnsupportedOperationException("Não foi possível cadastrar o pedido.");
        }
    }

    private void validateEnoughStock(List<Item> products) {
        products.forEach(product -> {
            ProductFindByIdResponseDTO productFound = productClient.findById(product.getProductId());

            boolean hasEnoughProducts = productFound.quantity().compareTo(product.getQuantity()) >= 0;

            if (!hasEnoughProducts) {
                throw new UnsupportedOperationException("Sem disponibilidade do produto '" +
                        productFound.name() + "' em estoque");
            }
        });
    }

    private void manageInStockProducts(List<Item> products) {
        products.forEach(product -> {
            StockProductUpdateRequestDTO stockProductUpdateRequestDTO = new StockProductUpdateRequestDTO(
                    product.getProductId(),
                    product.getQuantity(),
                    StockActionEnum.REMOVE
            );

            this.orderMessagingGateway.updateStockProduct(new GenericMessage(stockProductUpdateRequestDTO));
        });

    }

    private void validateCustomer(Long customerId) {
        if (Objects.isNull(customerClient.findById(customerId))) {
            throw new NotFoundException(String.format("Customer with ID '%s' not found.", customerId));
        }
    }

    public Order findById(Long id) {
        validateExistence(id);
        return this.orderRepository.findById(id).orElseThrow(() -> createNotFoundOrderException(id));
    }

    private void validateExistence(Long id) {
        if (!this.orderRepository.existsById(id)) throw createNotFoundOrderException(id);
    }

    private NotFoundException createNotFoundOrderException(Long id) {
        return new NotFoundException(String.format("Order with ID '%s' not found.", id));
    }
}