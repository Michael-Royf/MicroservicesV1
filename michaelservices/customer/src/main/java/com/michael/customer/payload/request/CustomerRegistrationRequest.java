package com.michael.customer.payload.request;

import com.michael.customer.entity.Customer;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerRegistrationRequest {
    private  String firstName;
    private  String lastName;
    private  String email;


}
