package com.reporting.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "dim_station", schema = "reporting_db")
public class DimStation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rpt_station_seq_gen")
    @SequenceGenerator(name = "rpt_station_seq_gen", sequenceName = "rpt_station_seq", schema = "reporting_db", allocationSize = 1)
    @Column(name = "station_id")
    private Long stationId;

    @Column(name = "station_name", nullable = false, length = 100)
    private String stationName;

    @Column(name = "station_code", nullable = false, unique = true, length = 20)
    private String stationCode;

    @Column(name = "location", length = 255)
    private String location;

    @OneToMany(mappedBy = "station", fetch = FetchType.LAZY)
    private List<MapRouteStation> routeStations;

    @OneToMany(mappedBy = "entryStation", fetch = FetchType.LAZY)
    private Set<FactTripRecord> entryTrips;

    @OneToMany(mappedBy = "exitStation", fetch = FetchType.LAZY)
    private Set<FactTripRecord> exitTrips;
}