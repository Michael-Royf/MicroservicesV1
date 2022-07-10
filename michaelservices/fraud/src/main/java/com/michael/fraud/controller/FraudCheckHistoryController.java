package com.michael.fraud.controller;

import com.michael.fraud.payload.response.FraudCheckResponse;
import com.michael.fraud.service.FraudCheckHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fraud-check")
@RequiredArgsConstructor
public class FraudCheckHistoryController {
    @Autowired
    private final FraudCheckHistoryService service;


    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable(name = "customerId") Integer customerId) {
        boolean isFraudulentCustomer = service.isFraudulentCustomer(customerId);
        return new FraudCheckResponse(isFraudulentCustomer);
    }
}
