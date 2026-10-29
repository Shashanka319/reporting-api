package com.reporting.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class DimMetroCardUpdateRequest {
    private BigDecimal balance;
    private String status;
}