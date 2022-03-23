package com.microservice.customer.service;

import com.microservice.clients.fraud.FraudCheckResponse;
import com.microservice.clients.fraud.FraudClient;
import com.microservice.clients.notification.NotificationClient;
import com.microservice.clients.notification.NotificationRequest;
import com.microservice.customer.dto.CustomerRequest;
import com.microservice.customer.dto.CustomerResponse;
import com.microservice.customer.model.Customer;
import com.microservice.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;
//    private final RabbitMessageQueueProducer producer;

    public CustomerResponse saveCustomer(CustomerRequest customerRequest) {
        Customer customerSaved = repository.saveAndFlush(Customer.builder()
                                                                 .firstName(customerRequest.getFirstName())
                                                                 .lastName(customerRequest.getLastName())
                                                                 .email(customerRequest.getEmail())
                                                                 .build());

        // Client service
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customerSaved.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Fraudster");
        }

        NotificationRequest notificationRequest = NotificationRequest.builder()
                                                                     .toCustomerId(customerSaved.getId())
                                                                     .toCustomerName(customerSaved.getEmail())
                                                                     .message(String.format("Hi %s, How are you?", customerSaved.getFirstName()))
                                                                     .build();

        // Notification service
        // No need to send notification though Notification Service. Now, it is sending the notification (message)
        // to the message queue
        notificationClient.sendNotification(notificationRequest);
//        producer.publish(notificationRequest, "internal.exchange", "internal.notification.routing-key");

        return CustomerResponse.builder()
                               .firstName(customerSaved.getFirstName())
                               .lastName(customerSaved.getLastName())
                               .email(customerSaved.getEmail())
                               .fraudulent(fraudCheckResponse.isFraudster())
                               .build();
    }

    public List<CustomerResponse> getAll() {
        return repository.findAll()
                         .stream()
                         .map(customer -> {
                             FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());
                             return CustomerResponse.builder()
                                             .firstName(customer.getFirstName())
                                             .lastName(customer.getLastName())
                                             .email(customer.getEmail())
                                             .fraudulent(fraudCheckResponse.isFraudster())
                                             .build();
                         })
                         .collect(Collectors.toList());
    }

}
