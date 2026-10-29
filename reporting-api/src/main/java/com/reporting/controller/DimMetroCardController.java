package com.reporting.controller;

import com.reporting.exception.DimMetroCardNotFoundException;
import com.reporting.model.DimMetroCardRequest;
import com.reporting.model.DimMetroCardTO;
import com.reporting.model.DimMetroCardUpdateRequest;
import com.reporting.service.DimMetroCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/reporting/cards")
public class DimMetroCardController {

    @Autowired
    private DimMetroCardService cardService;

    @GetMapping
    public ResponseEntity<List<DimMetroCardTO>> getAllCards() {
        try {
            return ResponseEntity.ok(cardService.findAllCards());
        } catch (DimMetroCardNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DimMetroCardTO> getCardById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(cardService.findCardById(id));
        } catch (DimMetroCardNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/card-number/{cardNumber}")
    public ResponseEntity<DimMetroCardTO> getCardByNumber(@PathVariable String cardNumber) {
        try {
            return ResponseEntity.ok(cardService.findByCardNumber(cardNumber));
        } catch (DimMetroCardNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Long> saveCard(@RequestBody DimMetroCardRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(cardService.save(request));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateCard(@PathVariable Long id, @RequestBody DimMetroCardUpdateRequest request) {
        try {
            return ResponseEntity.ok(cardService.update(id, request));
        } catch (DimMetroCardNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCard(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(cardService.deleteCardById(id));
        } catch (DimMetroCardNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}