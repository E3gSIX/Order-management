package com.e3gsix.fiap.tech_challenge_4_order_management.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public record ProductFindByIdResponseDTO(
        String id,
        String name,
        String description,
        BigDecimal price,
        TypeEnum type,
        BigInteger quantity
) {

}
