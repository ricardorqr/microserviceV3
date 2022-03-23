package com.microservice.customer.controller;

import com.microservice.customer.dto.CustomerRequest;
import com.microservice.customer.dto.CustomerResponse;
import com.microservice.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@Slf4j
@AllArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping("/")
    @Operation(summary = "Insert a new customer")
    public CustomerResponse saveCustomer(@RequestBody CustomerRequest customerRequest) {
        log.info("New customer: {}", customerRequest);
        return service.saveCustomer(customerRequest);
    }

    @GetMapping("/")
    @Operation(summary = "Return a list of all customers")
    public List<CustomerResponse> getAllCustomers() {
        return service.getAll();
    }

}
