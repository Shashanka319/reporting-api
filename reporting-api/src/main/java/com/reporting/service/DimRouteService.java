package com.reporting.service;

import com.reporting.exception.DimRouteNotFoundException;
import com.reporting.model.DimRouteRequest;
import com.reporting.model.DimRouteTO;
import com.reporting.model.DimRouteUpdateRequest;

import java.util.List;

public interface DimRouteService {
    List<DimRouteTO> findAllRoutes() throws DimRouteNotFoundException;
    DimRouteTO findRouteById(Long id) throws DimRouteNotFoundException;
    Long save(DimRouteRequest request);
    Long update(Long id, DimRouteUpdateRequest request) throws DimRouteNotFoundException;
    String deleteById(Long id) throws DimRouteNotFoundException;
}