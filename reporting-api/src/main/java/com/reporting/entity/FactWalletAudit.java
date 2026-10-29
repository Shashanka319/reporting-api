package com.reporting.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "fact_wallet_audit", schema = "reporting_db")
public class FactWalletAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rpt_txn_seq_gen")
    @SequenceGenerator(name = "rpt_txn_seq_gen", sequenceName = "rpt_txn_seq", schema = "reporting_db", allocationSize = 1)
    @Column(name = "txn_id")
    private Long transactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    private DimMetroCard metroCard;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "txn_type", nullable = false, length = 30)
    private String transactionType;

    @Column(name = "txn_date")
    private LocalDateTime transactionDate;
}