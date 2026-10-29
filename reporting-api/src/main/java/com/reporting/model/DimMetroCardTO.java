package com.reporting.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DimMetroCardTO {
    private Long cardId;
    private String cardNumber;
    private BigDecimal balance;
    private String status;
    private Long userId;
    private LocalDateTime issuedDate;
}