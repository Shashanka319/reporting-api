package com.reporting.controller;

import com.reporting.exception.DimRouteNotFoundException;
import com.reporting.exception.DimStationNotFoundException;
import com.reporting.exception.MapRouteStationNotFoundException;
import com.reporting.model.MapRouteStationRequest;
import com.reporting.model.MapRouteStationTO;
import com.reporting.service.MapRouteStationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/reporting/route-stations")
public class MapRouteStationController {

    @Autowired
    private MapRouteStationService mapRouteStationService;

    @GetMapping
    public ResponseEntity<List<MapRouteStationTO>> getAllMappings() {
        log.info("Inside MapRouteStationController.getAllMappings");
        try {
            return ResponseEntity.ok(mapRouteStationService.findAllRouteStations());
        } catch (MapRouteStationNotFoundException e) {
            log.error("Route station mappings not found: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Exception occurred while fetching route-station mappings", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/route/{routeId}")
    public ResponseEntity<List<MapRouteStationTO>> getStationsByRouteId(@PathVariable Long routeId) {
        log.info("Inside MapRouteStationController.getStationsByRouteId: {}", routeId);
        try {
            return ResponseEntity.ok(mapRouteStationService.findStationsByRouteId(routeId));
        } catch (MapRouteStationNotFoundException e) {
            log.error("No stations found for route: {}", routeId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Exception occurred while fetching stations for route: {}", routeId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/station/{stationId}")
    public ResponseEntity<List<MapRouteStationTO>> getRoutesByStationId(@PathVariable Long stationId) {
        log.info("Inside MapRouteStationController.getRoutesByStationId: {}", stationId);
        try {
            return ResponseEntity.ok(mapRouteStationService.findRoutesByStationId(stationId));
        } catch (MapRouteStationNotFoundException e) {
            log.error("No routes found for station: {}", stationId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Exception occurred while fetching routes for station: {}", stationId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/route/{routeId}/station/{stationId}")
    public ResponseEntity<MapRouteStationTO> getMapping(@PathVariable Long routeId, @PathVariable Long stationId) {
        log.info("Inside MapRouteStationController.getMapping: routeId {}, stationId {}", routeId, stationId);
        try {
            return ResponseEntity.ok(mapRouteStationService.findByRouteIdAndStationId(routeId, stationId));
        } catch (MapRouteStationNotFoundException e) {
            log.error("Mapping not found for route: {} and station: {}", routeId, stationId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Exception occurred while fetching mapping", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<String> assignStationToRoute(@RequestBody MapRouteStationRequest request) {
        log.info("Inside MapRouteStationController.assignStationToRoute: {}", request);
        try {
            String result = mapRouteStationService.assignStationToRoute(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (DimRouteNotFoundException | DimStationNotFoundException e) {
            log.error("Entity not found while assigning station to route: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Exception occurred while assigning station to route", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/route/{routeId}/station/{stationId}")
    public ResponseEntity<String> updateSequence(@PathVariable Long routeId,
                                                 @PathVariable Long stationId,
                                                 @RequestParam Long sequenceNo) {
        log.info("Inside MapRouteStationController.updateSequence: routeId {}, stationId {}, sequenceNo {}", routeId, stationId, sequenceNo);
        try {
            return ResponseEntity.ok(mapRouteStationService.updateSequenceNo(routeId, stationId, sequenceNo));
        } catch (MapRouteStationNotFoundException e) {
            log.error("Mapping not found for sequence update: route {}, station {}", routeId, stationId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Exception occurred while updating sequence", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/route/{routeId}/station/{stationId}")
    public ResponseEntity<String> removeStationFromRoute(@PathVariable Long routeId, @PathVariable Long stationId) {
        log.info("Inside MapRouteStationController.removeStationFromRoute: routeId {}, stationId {}", routeId, stationId);
        try {
            return ResponseEntity.ok(mapRouteStationService.removeStationFromRoute(routeId, stationId));
        } catch (MapRouteStationNotFoundException e) {
            log.error("Mapping not found for removal: route {}, station {}", routeId, stationId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Exception occurred while removing station from route", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}