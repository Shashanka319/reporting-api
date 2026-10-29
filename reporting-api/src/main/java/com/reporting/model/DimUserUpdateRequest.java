package com.reporting.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DimUserUpdateRequest {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String passwordHash;
    private String role;
}