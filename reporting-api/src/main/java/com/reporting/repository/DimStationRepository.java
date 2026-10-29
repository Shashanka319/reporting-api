package com.reporting.repository;

import com.reporting.entity.DimStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DimStationRepository extends JpaRepository<DimStation, Long> {
    Optional<DimStation> findByStationCode(String stationCode);
    Optional<DimStation> findByStationName(String stationName);
}