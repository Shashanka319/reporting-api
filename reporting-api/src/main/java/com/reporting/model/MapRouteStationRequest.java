package com.reporting.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MapRouteStationRequest {
    private Long routeId;
    private Long stationId;
    private Long sequenceNo;
}