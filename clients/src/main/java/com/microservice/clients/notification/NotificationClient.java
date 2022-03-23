package com.microservice.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "service-notification", path = "/notification")
public interface NotificationClient {

    @PostMapping()
    void sendNotification(NotificationRequest notificationRequest);

}
