package com.michael.notification.payload.request;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class NotificationRequest {
    private Integer toCustomerId;
    private String toCustomerEmail;
    private String message;
}
