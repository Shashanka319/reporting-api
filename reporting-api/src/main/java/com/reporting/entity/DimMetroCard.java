package com.reporting.entity;

import com.reporting.entity.DimUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "dim_metro_card", schema = "reporting_db")
public class DimMetroCard {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rpt_card_seq_gen")
    @SequenceGenerator(name = "rpt_card_seq_gen", sequenceName = "rpt_card_seq", schema = "reporting_db", allocationSize = 1)
    @Column(name = "card_id")
    private Long cardId;

    @Column(name = "card_number", nullable = false, unique = true, length = 30)
    private String cardNumber;

    @Column(name = "balance", precision = 10, scale = 2)
    private BigDecimal balance;

   // @Column(name = "balance")
   // private Double balance;

    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "issued_date")
    private LocalDateTime issuedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private DimUser user;

    @OneToMany(mappedBy = "metroCard", fetch = FetchType.LAZY)
    private Set<FactTripRecord> trips;

    @OneToMany(mappedBy = "metroCard", fetch = FetchType.LAZY)
    private Set<FactWalletAudit> walletAudits;

    @OneToMany(mappedBy = "metroCard", fetch = FetchType.LAZY)
    private Set<FactPaymentTransaction> paymentTransactions;
}