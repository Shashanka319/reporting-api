package com.reporting.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class DimMetroCardRequest {
    private String cardNumber;
    private BigDecimal balance;
    private String status;
    private Long userId;
    private LocalDateTime issuedDate;
}