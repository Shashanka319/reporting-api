package com.reporting.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MapRouteStationTO {
    private Long routeId;
    private Long stationId;
    private Long sequenceNo;
}