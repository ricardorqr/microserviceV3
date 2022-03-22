package com.microservice.service.customer.service;

import com.microservice.service.customer.dto.CustomerRequest;
import com.microservice.service.customer.dto.CustomerResponse;
import com.microservice.service.customer.model.Customer;
import com.microservice.service.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
//    private final RestTemplate restTemplate;
//    private final FraudClient fraudClient;
//    private final NotificationClient notificationClient;
//    private final RabbitMessageQueueProducer producer;

    public CustomerResponse saveCustomer(CustomerRequest customerRequest) {
        Customer customer = Customer.builder()
                                    .firstName(customerRequest.getFirstName())
                                    .lastName(customerRequest.getLastName())
                                    .email(customerRequest.getEmail())
                                    .build();
        Customer customerSaved = repository.saveAndFlush(customer);

        // The code below will be replaced to fraudClient.isFraudster
//        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject("http://service-fraud/fraud-check/{customerId}",
//                FraudCheckResponse.class,
//                customer.getId());

        // Client service
//        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

//        if (fraudCheckResponse.isFraudster()) {
//            throw new IllegalStateException("Fraudster");
//        }
//
//        NotificationRequest notificationRequest = NotificationRequest.builder()
//                                                                     .toCustomerId(customer.getId())
//                                                                     .toCustomerName(customer.getEmail())
//                                                                     .message(String.format("Hi %s, How are you?", customer.getFirstName()))
//                                                                     .build();

        // Notification service
        // No need to send notification though Notification Service. Now, it is sending the notification (message)
        // to the message queue
//        notificationClient.sendNotification(notificationRequest);
//        producer.publish(notificationRequest, "internal.exchange", "internal.notification.routing-key");

        return CustomerResponse.builder()
                .firstName(customerSaved.getFirstName())
                .lastName(customerSaved.getLastName())
                .email(customerSaved.getEmail()).build();
    }

    public List<CustomerResponse> getAll() {
        return repository.findAll()
                         .stream()
                         .map(customer -> CustomerResponse.builder()
                                                          .firstName(customer.getFirstName())
                                                          .lastName(customer.getLastName())
                                                          .email(customer.getEmail())
                                                          .build())
                         .collect(Collectors.toList());
    }

}
