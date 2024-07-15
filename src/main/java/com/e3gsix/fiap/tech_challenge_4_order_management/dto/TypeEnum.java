package com.e3gsix.fiap.tech_challenge_4_order_management.dto;

public enum TypeEnum {
    LIGHT(1),
    NORMAL(2),
    HEAVY(3);

    private int id;

    TypeEnum(int id) {
        this.id = id;
    }
}
