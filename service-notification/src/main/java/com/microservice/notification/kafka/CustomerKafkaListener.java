package com.microservice.notification.kafka;

import com.microservice.clients.notification.NotificationRequest;
import com.microservice.notification.model.Notification;
import com.microservice.notification.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerKafkaListener {

    private final NotificationRepository repository;

    @KafkaListener(topics = "customerKafkaTopic",
            groupId = "customerKafkaGroup",
            containerFactory = "kafkaListenerContainerFactory")
    public void listener(NotificationRequest notificationRequest) {
        log.info("Consuming the message: {}", notificationRequest);

        Notification notification = Notification.builder()
                                                .toCustomerId(notificationRequest.getToCustomerId())
                                                .toCustomerEmail(notificationRequest.getToCustomerName())
                                                .sender("Ricardo")
                                                .message(notificationRequest.getMessage())
                                                .date(LocalDateTime.now())
                                                .build();

        repository.save(notification);
    }

}
