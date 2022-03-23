package com.microservice.notification.service;

import com.microservice.clients.notification.NotificationRequest;
import com.microservice.notification.model.Notification;
import com.microservice.notification.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository repository;

    public void sendNotification(NotificationRequest notificationRequest) {
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
