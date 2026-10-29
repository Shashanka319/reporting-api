package com.reporting.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "dim_users", schema = "reporting_db")
public class DimUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rpt_user_seq_gen")
    @SequenceGenerator(name = "rpt_user_seq_gen", sequenceName = "rpt_user_seq", schema = "reporting_db", allocationSize = 1)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "full_name", nullable = false, length = 50)
    private String fullName;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "password_hash", nullable = false, length = 100)
    private String passwordHash;

    @Column(name = "role", length = 20)
    private String role;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<DimMetroCard> metroCards;
}