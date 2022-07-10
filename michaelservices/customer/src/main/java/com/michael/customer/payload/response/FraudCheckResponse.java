package com.michael.customer.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FraudCheckResponse implements Serializable {
    private  boolean isFraudster;
}
