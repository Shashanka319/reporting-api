package com.reporting.controller;

import com.reporting.exception.DimRouteNotFoundException;
import com.reporting.model.DimRouteRequest;
import com.reporting.model.DimRouteTO;
import com.reporting.model.DimRouteUpdateRequest;
import com.reporting.service.DimRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/reporting/routes")
public class DimRouteController {

    @Autowired
    private DimRouteService routeService;

    @GetMapping
    public ResponseEntity<List<DimRouteTO>> getAllRoutes() {
        log.info("Inside DimRouteController.getAllRoutes");
        try {
            return ResponseEntity.ok(routeService.findAllRoutes());
        } catch (DimRouteNotFoundException e) {
            log.error("Routes not found: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Exception occurred while fetching routes", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DimRouteTO> getRouteById(@PathVariable Long id) {
        log.info("Inside DimRouteController.getRouteById: {}", id);
        try {
            return ResponseEntity.ok(routeService.findRouteById(id));
        } catch (DimRouteNotFoundException e) {
            log.error("Route not found with ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Exception occurred while fetching route ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Long> saveRoute(@RequestBody DimRouteRequest request) {
        log.info("Inside DimRouteController.saveRoute: {}", request);
        try {
            Long routeId = routeService.save(request);
            log.info("Route saved successfully with ID: {}", routeId);
            return ResponseEntity.status(HttpStatus.CREATED).body(routeId);
        } catch (Exception e) {
            log.error("Exception occurred while saving route", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateRoute(@PathVariable Long id, @RequestBody DimRouteUpdateRequest request) {
        log.info("Inside DimRouteController.updateRoute: ID {}, Request {}", id, request);
        try {
            Long updatedId = routeService.update(id, request);
            return ResponseEntity.ok(updatedId);
        } catch (DimRouteNotFoundException e) {
            log.error("Route not found for update ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Exception occurred while updating route ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRouteById(@PathVariable Long id) {
        log.info("Inside DimRouteController.deleteRouteById: {}", id);
        try {
            return ResponseEntity.ok(routeService.deleteById(id));
        } catch (DimRouteNotFoundException e) {
            log.error("Route not found for deletion ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Exception occurred while deleting route ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}