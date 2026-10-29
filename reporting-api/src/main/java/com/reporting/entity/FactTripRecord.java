package com.reporting.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "fact_trip_record", schema = "reporting_db")
public class FactTripRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rpt_trip_seq_gen")
    @SequenceGenerator(name = "rpt_trip_seq_gen", sequenceName = "rpt_trip_seq", schema = "reporting_db", allocationSize = 1)
    @Column(name = "trip_id")
    private Long tripId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    private DimMetroCard metroCard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entry_station_id", nullable = false)
    private DimStation entryStation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exit_station_id")
    private DimStation exitStation;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "fare_amount", precision = 10, scale = 2)
    private BigDecimal fareAmount;
}