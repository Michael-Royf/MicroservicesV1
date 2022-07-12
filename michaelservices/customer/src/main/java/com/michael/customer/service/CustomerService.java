package com.michael.customer.service;

import com.michael.amqp.RabbitMQMessageProducer;
import com.michael.clients.fraud.FraudCheckResponse;
import com.michael.clients.fraud.FraudClient;
import com.michael.clients.notification.NotificationClient;
import com.michael.clients.notification.NotificationRequest;
import com.michael.customer.entity.Customer;
import com.michael.customer.payload.request.CustomerRegistrationRequest;
import com.michael.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final RestTemplate restTemplate;
    @Autowired
    private final FraudClient fraudClient;

    @Autowired
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

//    @Autowired
//    private final NotificationClient notificationClient;


    public void registerCustomer(CustomerRegistrationRequest customerRequest) {

        Customer customer = Customer.builder()
                .firstName(customerRequest.getFirstName())
                .lastName(customerRequest.getLastName())
                .email(customerRequest.getEmail())
                .build();
        //TODO: check if email valid
        //TODO: check if email not taken
        //TODO: check if fraudster
        customerRepository.saveAndFlush(customer);

//        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
//                "http://FRAUD/api/v1/fraud-check/{customerId}",
//                FraudCheckResponse.class,
//                customer.getId()
//        );

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Fraudster");
        }
        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to Amigoscode...",
                        customer.getFirstName()));

       // notificationClient.sendNotification(notificationRequest);
        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );
        //TODO: send notification
    }

}
