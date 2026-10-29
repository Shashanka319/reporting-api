package com.reporting.repository;

import com.reporting.entity.MapRouteStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MapRouteStationRepository extends JpaRepository<MapRouteStation, MapRouteStation.MapRouteStationPK> {
    List<MapRouteStation> findByRouteRouteIdOrderBySequenceNoAsc(Long routeId);
    List<MapRouteStation> findByStationStationId(Long stationId);
}