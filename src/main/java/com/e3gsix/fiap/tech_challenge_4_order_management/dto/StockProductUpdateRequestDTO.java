package com.e3gsix.fiap.tech_challenge_4_order_management.dto;

import java.math.BigInteger;

public record StockProductUpdateRequestDTO(
        String productId,
        BigInteger quantity,
        StockActionEnum action
) {
}
