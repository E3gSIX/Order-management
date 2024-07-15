package com.e3gsix.fiap.tech_challenge_4_order_management.clients;

import com.e3gsix.fiap.tech_challenge_4_order_management.dto.ProductFindByIdResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "products-client", url = "${stock-products.url}/products")
public interface ProductClient {

    @GetMapping(value = "/{productId}")
    ProductFindByIdResponseDTO findById(@PathVariable(value = "productId") String productId);

}
