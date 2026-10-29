package com.reporting.service;

import com.reporting.exception.DimStationNotFoundException;
import com.reporting.model.DimStationRequest;
import com.reporting.model.DimStationTO;
import com.reporting.model.DimStationUpdateRequest;

import java.util.List;

public interface DimStationService {
    List<DimStationTO> findAllStations() throws DimStationNotFoundException;
    DimStationTO findStationById(Long id) throws DimStationNotFoundException;
    DimStationTO findByCode(String code) throws DimStationNotFoundException;
    Long save(DimStationRequest request);
    Long update(Long id, DimStationUpdateRequest request) throws DimStationNotFoundException;
    String deleteById(Long id) throws DimStationNotFoundException;
}