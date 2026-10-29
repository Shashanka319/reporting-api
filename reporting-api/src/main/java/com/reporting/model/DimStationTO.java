package com.reporting.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DimStationTO {
    private Long stationId;
    private String stationName;
    private String stationCode;
    private String location;
}