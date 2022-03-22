package com.microservice.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-fraud", path = "/fraud-check")
public interface FraudClient {

    @GetMapping("/{customerId}")
    FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId);

}
