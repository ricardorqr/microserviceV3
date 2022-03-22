package com.microseervice.fraud.service;

import com.microseervice.fraud.model.Fraud;
import com.microseervice.fraud.repository.FraudRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudService {

    private FraudRepository repository;

    public boolean isFraudulentCustomer(Integer customerId) {
        Fraud fraud = Fraud.builder()
                           .customerId(customerId)
                           .isFraudster(false)
                           .date(LocalDateTime.now())
                           .build();

        repository.save(fraud);
        return false;
    }

}
