package com.michael.customer.service;

import com.michael.customer.entity.Customer;
import com.michael.customer.payload.request.CustomerRegistrationRequest;
import com.michael.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public void registerCustomer(CustomerRegistrationRequest customerRequest) {

        Customer customer = Customer.builder()
                .firstName(customerRequest.getFirstName())
                .lastName(customerRequest.getLastName())
                .email(customerRequest.getEmail())
                .build();
        //TODO: check if email valid
        //TODO: check if email not taken

        customerRepository.save(customer);
    }

}
