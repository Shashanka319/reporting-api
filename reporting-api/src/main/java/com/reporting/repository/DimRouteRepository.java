package com.reporting.repository;

import com.reporting.entity.DimRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DimRouteRepository extends JpaRepository<DimRoute, Long> {
    List<DimRoute> findByRouteNameContainingIgnoreCase(String routeName);
}