package com.microseervice.fraud.controller;

import com.microseervice.fraud.service.FraudService;
import com.microservice.clients.fraud.FraudCheckResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fraud-check")
@AllArgsConstructor
@Slf4j
public class FraudController {

    private final FraudService service;

    @GetMapping("/{customerId}")
    @Operation(summary = "Check if a customer is fraudulent")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId) {
        log.info("Fraud check request for the customer: {}", customerId);
        return FraudCheckResponse.builder()
                                 .isFraudster(service.isFraudulentCustomer(customerId))
                                 .build();
    }

}
