package com.reporting.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class FactTripRecordUpdateRequest {
    private Long exitStationId;
    private LocalDateTime endTime;
    private BigDecimal fareAmount;
}