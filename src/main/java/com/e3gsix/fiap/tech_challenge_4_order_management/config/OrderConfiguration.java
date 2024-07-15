package com.e3gsix.fiap.tech_challenge_4_order_management.config;

import com.e3gsix.fiap.tech_challenge_4_order_management.gateway.OrderMessagingGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.http.dsl.Http;
import org.springframework.messaging.MessageChannel;

@Configuration
public class OrderConfiguration {

    @Value("${stock-products.url}")
    private String STOCK_URL;

    @Bean
    public MessageChannel stockMessageChannel() {
        DirectChannel directChannel = new DirectChannel();
        directChannel.setFailover(false);
        return directChannel;
    }

    @Bean
    public IntegrationFlow stockProductIntegrationFlow() {
        return IntegrationFlow.from(OrderMessagingGateway.STOCK_PRODUCT_CHANNEL)
                .handle(
                        Http.outboundGateway(STOCK_URL + "/consumer-stock-product-update")
                                .httpMethod(HttpMethod.POST)
                )
                .log()
                .bridge()
                .get();
    }

}
