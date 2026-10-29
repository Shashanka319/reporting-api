package com.reporting.controller;

import com.reporting.exception.FactPaymentTransactionNotFoundException;
import com.reporting.model.FactPaymentTransactionRequest;
import com.reporting.model.FactPaymentTransactionTO;
import com.reporting.model.FactPaymentTransactionUpdateRequest;
import com.reporting.service.FactPaymentTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reporting/payments")
public class FactPaymentTransactionController {

    @Autowired
    private FactPaymentTransactionService paymentService;

    @GetMapping
    public ResponseEntity<List<FactPaymentTransactionTO>> getAllPayments() {
        try {
            return ResponseEntity.ok(paymentService.findAllPayments());
        } catch (FactPaymentTransactionNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactPaymentTransactionTO> getPaymentById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(paymentService.findPaymentById(id));
        } catch (FactPaymentTransactionNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Long> recordPayment(@RequestBody FactPaymentTransactionRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.save(request));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updatePayment(@PathVariable Long id, @RequestBody FactPaymentTransactionUpdateRequest request) {
        try {
            return ResponseEntity.ok(paymentService.update(id, request));
        } catch (FactPaymentTransactionNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(paymentService.deleteById(id));
        } catch (FactPaymentTransactionNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}