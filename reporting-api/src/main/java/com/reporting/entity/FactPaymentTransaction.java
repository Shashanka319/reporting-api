package com.reporting.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "fact_payment_transaction", schema = "reporting_db")
public class FactPaymentTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rpt_payment_seq_gen")
    @SequenceGenerator(name = "rpt_payment_seq_gen", sequenceName = "rpt_payment_seq", schema = "reporting_db", allocationSize = 1)
    @Column(name = "payment_id")
    private Long paymentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    private DimMetroCard metroCard;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "payment_method", length = 30)
    private String paymentMethod;

    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;
}