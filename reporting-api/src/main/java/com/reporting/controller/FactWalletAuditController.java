package com.reporting.controller;

import com.reporting.exception.FactWalletAuditNotFoundException;
import com.reporting.model.FactWalletAuditRequest;
import com.reporting.model.FactWalletAuditTO;
import com.reporting.model.FactWalletAuditUpdateRequest;
import com.reporting.service.FactWalletAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reporting/wallet-audits")
public class FactWalletAuditController {

    @Autowired
    private FactWalletAuditService auditService;

    @GetMapping
    public ResponseEntity<List<FactWalletAuditTO>> getAllAudits() {
        try {
            return ResponseEntity.ok(auditService.findAllAudits());
        } catch (FactWalletAuditNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactWalletAuditTO> getAuditById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(auditService.findAuditById(id));
        } catch (FactWalletAuditNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Long> saveAudit(@RequestBody FactWalletAuditRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(auditService.save(request));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateAudit(@PathVariable Long id, @RequestBody FactWalletAuditUpdateRequest request) {
        try {
            return ResponseEntity.ok(auditService.update(id, request));
        } catch (FactWalletAuditNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAudit(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(auditService.deleteById(id));
        } catch (FactWalletAuditNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}