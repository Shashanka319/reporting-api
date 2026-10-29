package com.reporting.controller;

import com.reporting.exception.DimUserNotFoundException;
import com.reporting.model.DimUserRequest;
import com.reporting.model.DimUserTO;
import com.reporting.model.DimUserUpdateRequest;
import com.reporting.service.DimUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/reporting/users")
public class DimUserController {

    @Autowired
    private DimUserService userService;

    @GetMapping
    public ResponseEntity<List<DimUserTO>> getAllUsers() {
        log.info("Fetching all reporting users");
        try {
            return ResponseEntity.ok(userService.findAllUsers());
        } catch (DimUserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DimUserTO> getUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.findUserById(id));
        } catch (DimUserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/name")
    public ResponseEntity<List<DimUserTO>> getUserByName(@RequestParam String name) {
        try {
            return ResponseEntity.ok(userService.findUserByName(name));
        } catch (DimUserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Long> saveUser(@RequestBody DimUserRequest request) {
        try {
            Long userId = userService.save(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(userId);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateUser(@PathVariable Long id, @RequestBody DimUserUpdateRequest request) {
        try {
            return ResponseEntity.ok(userService.update(id, request));
        } catch (DimUserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.deleteUserById(id));
        } catch (DimUserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/name")
    public ResponseEntity<String> deleteUserByName(@RequestParam String name) {
        try {
            return ResponseEntity.ok(userService.deleteUserByName(name));
        } catch (DimUserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}