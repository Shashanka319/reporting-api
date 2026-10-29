package com.reporting.controller;

import com.reporting.exception.FactTripRecordNotFoundException;
import com.reporting.model.FactTripRecordRequest;
import com.reporting.model.FactTripRecordTO;
import com.reporting.model.FactTripRecordUpdateRequest;
import com.reporting.service.FactTripRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reporting/trips")
public class FactTripRecordController {

    @Autowired
    private FactTripRecordService tripService;

    @GetMapping
    public ResponseEntity<List<FactTripRecordTO>> getAllTrips() {
        try {
            return ResponseEntity.ok(tripService.findAllTrips());
        } catch (FactTripRecordNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactTripRecordTO> getTripById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(tripService.findTripById(id));
        } catch (FactTripRecordNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/card/{cardId}")
    public ResponseEntity<List<FactTripRecordTO>> getTripsByCardId(@PathVariable Long cardId) {
        try {
            return ResponseEntity.ok(tripService.findTripsByCardId(cardId));
        } catch (FactTripRecordNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Long> recordTrip(@RequestBody FactTripRecordRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(tripService.save(request));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateTrip(@PathVariable Long id, @RequestBody FactTripRecordUpdateRequest request) {
        try {
            return ResponseEntity.ok(tripService.update(id, request));
        } catch (FactTripRecordNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTrip(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(tripService.deleteById(id));
        } catch (FactTripRecordNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}