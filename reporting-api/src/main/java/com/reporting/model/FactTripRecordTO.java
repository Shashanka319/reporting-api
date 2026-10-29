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
public class FactTripRecordTO {
    private Long tripId;
    private Long cardId;
    private Long entryStationId;
    private Long exitStationId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BigDecimal fareAmount;
}