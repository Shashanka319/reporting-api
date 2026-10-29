package com.reporting.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FactPaymentTransactionUpdateRequest {
    private String paymentMethod;
    private String status;
}