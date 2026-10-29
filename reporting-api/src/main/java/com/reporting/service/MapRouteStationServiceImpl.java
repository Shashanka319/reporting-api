package com.reporting.service;

import com.reporting.entity.DimRoute;
import com.reporting.entity.DimStation;
import com.reporting.entity.MapRouteStation;
import com.reporting.exception.DimRouteNotFoundException;
import com.reporting.exception.DimStationNotFoundException;
import com.reporting.exception.MapRouteStationNotFoundException;
import com.reporting.model.MapRouteStationRequest;
import com.reporting.model.MapRouteStationTO;
import com.reporting.repository.DimRouteRepository;
import com.reporting.repository.DimStationRepository;
import com.reporting.repository.MapRouteStationRepository;
import com.reporting.service.MapRouteStationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class MapRouteStationServiceImpl implements MapRouteStationService {

    @Autowired
    private MapRouteStationRepository mapRouteStationRepository;

    @Autowired
    private DimRouteRepository routeRepository;

    @Autowired
    private DimStationRepository stationRepository;

    @Override
    public List<MapRouteStationTO> findAllRouteStations() throws MapRouteStationNotFoundException {
        log.info("Fetching all route-station mappings");
        List<MapRouteStation> list = mapRouteStationRepository.findAll();
        if (list == null || list.isEmpty()) {
            log.error("No route-station mappings found");
            throw new MapRouteStationNotFoundException("Route-station mappings are empty");
        }
        return list.stream().map(this::mapToTO).toList();
    }

    @Override
    public MapRouteStationTO findByRouteIdAndStationId(Long routeId, Long stationId) throws MapRouteStationNotFoundException {
        log.info("Fetching route-station mapping for routeId: {} and stationId: {}", routeId, stationId);
        MapRouteStation.MapRouteStationPK pk = new MapRouteStation.MapRouteStationPK(routeId, stationId);
        return mapRouteStationRepository.findById(pk)
                .map(this::mapToTO)
                .orElseThrow(() -> new MapRouteStationNotFoundException("Mapping not found for Route: " + routeId + " and Station: " + stationId));
    }

    @Override
    public List<MapRouteStationTO> findStationsByRouteId(Long routeId) throws MapRouteStationNotFoundException {
        log.info("Fetching stations for routeId: {}", routeId);
        List<MapRouteStation> list = mapRouteStationRepository.findByRouteRouteIdOrderBySequenceNoAsc(routeId);
        if (list == null || list.isEmpty()) {
            throw new MapRouteStationNotFoundException("No stations mapped to Route ID: " + routeId);
        }
        return list.stream().map(this::mapToTO).toList();
    }

    @Override
    public List<MapRouteStationTO> findRoutesByStationId(Long stationId) throws MapRouteStationNotFoundException {
        log.info("Fetching routes for stationId: {}", stationId);
        List<MapRouteStation> list = mapRouteStationRepository.findByStationStationId(stationId);
        if (list == null || list.isEmpty()) {
            throw new MapRouteStationNotFoundException("No routes mapped to Station ID: " + stationId);
        }
        return list.stream().map(this::mapToTO).toList();
    }

    @Override
    @Transactional
    public String assignStationToRoute(MapRouteStationRequest request) throws DimRouteNotFoundException, DimStationNotFoundException {
        log.info("Assigning station to route: {}", request);
        DimRoute route = routeRepository.findById(request.getRouteId())
                .orElseThrow(() -> new DimRouteNotFoundException("Route not found with ID: " + request.getRouteId()));
        DimStation station = stationRepository.findById(request.getStationId())
                .orElseThrow(() -> new DimStationNotFoundException("Station not found with ID: " + request.getStationId()));

        MapRouteStation mapping = new MapRouteStation();
        mapping.setRoute(route);
        mapping.setStation(station);
        mapping.setSequenceNo(request.getSequenceNo());

        mapRouteStationRepository.save(mapping);
        log.info("Station {} assigned to Route {} at sequence {}", request.getStationId(), request.getRouteId(), request.getSequenceNo());
        return "Station assigned to route successfully";
    }

    @Override
    @Transactional
    public String updateSequenceNo(Long routeId, Long stationId, Long sequenceNo) throws MapRouteStationNotFoundException {
        log.info("Updating sequence for routeId: {}, stationId: {} to {}", routeId, stationId, sequenceNo);
        MapRouteStation.MapRouteStationPK pk = new MapRouteStation.MapRouteStationPK(routeId, stationId);
        MapRouteStation mapping = mapRouteStationRepository.findById(pk)
                .orElseThrow(() -> new MapRouteStationNotFoundException("Mapping not found for Route: " + routeId + " and Station: " + stationId));

        mapping.setSequenceNo(sequenceNo);
        mapRouteStationRepository.save(mapping);
        return "Sequence number updated successfully";
    }

    @Override
    @Transactional
    public String removeStationFromRoute(Long routeId, Long stationId) throws MapRouteStationNotFoundException {
        log.info("Removing stationId: {} from routeId: {}", stationId, routeId);
        MapRouteStation.MapRouteStationPK pk = new MapRouteStation.MapRouteStationPK(routeId, stationId);
        if (!mapRouteStationRepository.existsById(pk)) {
            throw new MapRouteStationNotFoundException("Mapping not found for Route: " + routeId + " and Station: " + stationId);
        }
        mapRouteStationRepository.deleteById(pk);
        return "Station removed from route successfully";
    }

    private MapRouteStationTO mapToTO(MapRouteStation mapping) {
        return new MapRouteStationTO(
                mapping.getRoute() != null ? mapping.getRoute().getRouteId() : null,
                mapping.getStation() != null ? mapping.getStation().getStationId() : null,
                mapping.getSequenceNo()
        );
    }
}