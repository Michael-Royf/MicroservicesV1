package com.michael.notification.rabbitmq;

import com.michael.clients.notification.NotificationRequest;
import com.michael.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class NotificationConsumer {
    @Autowired
    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumer(NotificationRequest notificationRequest) {
        log.info("consumed {} from queue ", notificationRequest);
        notificationService.send(notificationRequest);
    }

}
