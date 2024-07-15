package com.e3gsix.fiap.tech_challenge_4_order_management.clients;

import com.e3gsix.fiap.tech_challenge_4_order_management.dto.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-client", url = "${customer-management.url}/customers")
public interface CustomerClient {

    @GetMapping(value = "/{customerId}")
    CustomerDTO findById(@PathVariable(value = "customerId") Long customerId);

}
