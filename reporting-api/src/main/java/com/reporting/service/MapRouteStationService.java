package com.reporting.service;

import com.reporting.exception.DimRouteNotFoundException;
import com.reporting.exception.DimStationNotFoundException;
import com.reporting.exception.MapRouteStationNotFoundException;
import com.reporting.model.MapRouteStationRequest;
import com.reporting.model.MapRouteStationTO;

import java.util.List;

public interface MapRouteStationService {
    List<MapRouteStationTO> findAllRouteStations() throws MapRouteStationNotFoundException;
    MapRouteStationTO findByRouteIdAndStationId(Long routeId, Long stationId) throws MapRouteStationNotFoundException;
    List<MapRouteStationTO> findStationsByRouteId(Long routeId) throws MapRouteStationNotFoundException;
    List<MapRouteStationTO> findRoutesByStationId(Long stationId) throws MapRouteStationNotFoundException;
    String assignStationToRoute(MapRouteStationRequest request) throws DimRouteNotFoundException, DimStationNotFoundException;
    String updateSequenceNo(Long routeId, Long stationId, Long sequenceNo) throws MapRouteStationNotFoundException;
    String removeStationFromRoute(Long routeId, Long stationId) throws MapRouteStationNotFoundException;
}