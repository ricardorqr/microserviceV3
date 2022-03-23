package com.microservice.notification;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//        (
//        scanBasePackages = {
//                "com.microservice.notification",
//                "com.microservice.rabbitmq",
//        })
@EnableEurekaClient
@OpenAPIDefinition(
        info = @Info(
                title = "Notification API",
                version = "1.0",
                contact = @Contact(name = "Ricardo Ribeiro", url = "https://github.com/ricardorqr/microserviceV3", email = "ricardorqr@gmail.com"),
                license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0.html")
        )
)
public class NotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

    // Test to check payload
//    @Bean
//    CommandLineRunner commandLineRunner(RabbitMessageQueueProducer producer, NotificationConfig notificationConfig) {
//        return args -> producer.publish(new Person("Test Rico", 18),
//                notificationConfig.getInternalExchange(),
//                notificationConfig.getInternalNotificationRoutingKey());
//    }
//
//    record Person(String name, int age) { }

}
