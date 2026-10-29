package com.reporting.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class FactWalletAuditRequest {
    private Long cardId;
    private BigDecimal amount;
    private String txnType;
    private LocalDateTime txnDate;
}