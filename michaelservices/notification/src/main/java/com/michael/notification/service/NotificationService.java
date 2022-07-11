package com.michael.notification.service;

import com.michael.clients.notification.NotificationRequest;
import com.michael.notification.entity.Notification;
//import com.michael.notification.payload.request.NotificationRequest;
import com.michael.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationService {
    @Autowired
    private final NotificationRepository notificationRepository;

    public void send(NotificationRequest notificationRequest) {
        notificationRepository.save(
                Notification.builder()
                        .toCustomerId(notificationRequest.getToCustomerId())
                        .toCustomerEmail(notificationRequest.getToCustomerEmail())
                        .sender("Amigoscode")
                        .message(notificationRequest.getMessage())
                        .sentAt(LocalDateTime.now())
                        .build()
        );
    }


}