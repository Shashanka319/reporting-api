package com.reporting.controller;

import com.reporting.exception.DimStationNotFoundException;
import com.reporting.model.DimStationRequest;
import com.reporting.model.DimStationTO;
import com.reporting.model.DimStationUpdateRequest;
import com.reporting.service.DimStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reporting/stations")
public class DimStationController {

    @Autowired
    private DimStationService stationService;

    @GetMapping
    public ResponseEntity<List<DimStationTO>> getAllStations() {
        try {
            return ResponseEntity.ok(stationService.findAllStations());
        } catch (DimStationNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DimStationTO> getStationById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(stationService.findStationById(id));
        } catch (DimStationNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<DimStationTO> getStationByCode(@PathVariable String code) {
        try {
            return ResponseEntity.ok(stationService.findByCode(code));
        } catch (DimStationNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Long> saveStation(@RequestBody DimStationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stationService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateStation(@PathVariable Long id, @RequestBody DimStationUpdateRequest request) {
        try {
            return ResponseEntity.ok(stationService.update(id, request));
        } catch (DimStationNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStation(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(stationService.deleteById(id));
        } catch (DimStationNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}