package com.reporting.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DimUserTO {
    private Long userId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String passwordHash;
    private String role;
    private LocalDateTime createdAt;
    private Set<DimMetroCardTO> metroCards;
}