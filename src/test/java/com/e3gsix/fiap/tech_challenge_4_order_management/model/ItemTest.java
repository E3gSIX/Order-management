package com.e3gsix.fiap.tech_challenge_4_order_management.model;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ItemTest {

    @Test
    void itemModel_NoArgsConstructor_NullGetters() {
        Item item = new Item();
        assertNull(item.getProductId());
        assertNull(item.getQuantity());
    }

    @Test
    void itemModel_AllArgsConstructor_ValuedGetters() {
        Item item = new Item(1L, "669bc6709d2a3a79fcef302b", BigInteger.TEN);
        assertEquals("669bc6709d2a3a79fcef302b", item.getProductId());
        assertEquals(BigInteger.TEN, item.getQuantity());
    }
}