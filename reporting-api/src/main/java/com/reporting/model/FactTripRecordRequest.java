package com.reporting.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class FactTripRecordRequest {
    private Long cardId;
    private Long entryStationId;
    private Long exitStationId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BigDecimal fareAmount;
}