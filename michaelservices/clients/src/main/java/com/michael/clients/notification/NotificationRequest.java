package com.michael.clients.notification;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class NotificationRequest implements Serializable {
    private Integer toCustomerId;
    private String toCustomerEmail;
    private String message;
}
