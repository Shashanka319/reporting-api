package com.reporting.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class DimUserRequest {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String passwordHash;
    private String role;
    private LocalDateTime createdAt;
}