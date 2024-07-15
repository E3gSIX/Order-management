package com.e3gsix.fiap.tech_challenge_4_order_management.gateway;

import com.e3gsix.fiap.tech_challenge_4_order_management.dto.StockProductUpdateRequestDTO;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.GatewayHeader;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

@MessagingGateway
public interface OrderMessagingGateway {

    public static final String STOCK_PRODUCT_CHANNEL = "stock-product-channel";

    @Gateway(
            requestChannel = STOCK_PRODUCT_CHANNEL,
            replyTimeout = 5000, // 5s timeout for synchronous scenarios
            headers = @GatewayHeader( // Indicate empty return scenario (HTTP Status Code 201, empty body)
                    name = MessageHeaders.REPLY_CHANNEL,
                    expression = "@nullChannel"
            )
    )
    void updateStockProduct(Message<StockProductUpdateRequestDTO> stockProductUpdateRequestDTOMessage);
}
