package com.e3gsix.fiap.tech_challenge_4_order_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
