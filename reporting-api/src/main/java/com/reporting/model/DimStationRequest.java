package com.reporting.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DimStationRequest {
    private String stationName;
    private String stationCode;
    private String location;
}