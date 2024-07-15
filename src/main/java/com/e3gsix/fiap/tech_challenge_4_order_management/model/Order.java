package com.e3gsix.fiap.tech_challenge_4_order_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Entity(name = "orders") // "order" is a reserved keyword in MySQL
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long customerId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> items;

    public void validate() {
        if (this.customerId == null) {
            throw new UnsupportedOperationException("Pedidos devem ter um id de cliente.");
        }

        if (this.items.isEmpty()) {
            throw new UnsupportedOperationException("Pedidos devem ter pelo menos um produto.");
        }

        if (!hasValidQuantities()) {
            throw new UnsupportedOperationException(
                    "Os produtos de um pedido devem estar com uma quantidade acima de zero."
            );
        }
    }

    private boolean hasValidQuantities() {
        return this.items.stream()
                .filter(item -> item.getQuantity().compareTo(BigInteger.ZERO) <= 0)
                .toList()
                .isEmpty();
    }
}
