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
public class FactWalletAuditTO {
    private Long txnId;
    private Long cardId;
    private BigDecimal amount;
    private String txnType;
    private LocalDateTime txnDate;
}