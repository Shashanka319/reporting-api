package com.reporting.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "map_route_station", schema = "reporting_db")
@IdClass(MapRouteStation.MapRouteStationPK.class)
public class MapRouteStation {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", referencedColumnName = "route_id", nullable = false)
    private DimRoute route;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id", referencedColumnName = "station_id", nullable = false)
    private DimStation station;

    @Column(name = "sequence_no")
    private Long sequenceNo;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class MapRouteStationPK implements Serializable {
        private Long route;
        private Long station;
    }
}