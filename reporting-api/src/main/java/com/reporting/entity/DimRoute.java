package com.reporting.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "dim_route", schema = "reporting_db")
public class DimRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rpt_route_seq_gen")
    @SequenceGenerator(name = "rpt_route_seq_gen", sequenceName = "rpt_route_seq", schema = "reporting_db", allocationSize = 1)
    @Column(name = "route_id")
    private Long routeId;

    @Column(name = "route_name", nullable = false, length = 100)
    private String routeName;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<MapRouteStation> routeStations;
}