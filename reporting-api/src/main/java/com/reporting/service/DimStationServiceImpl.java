package com.reporting.service;

import com.reporting.entity.DimStation;
import com.reporting.exception.DimStationNotFoundException;
import com.reporting.model.DimStationRequest;
import com.reporting.model.DimStationTO;
import com.reporting.model.DimStationUpdateRequest;
import com.reporting.repository.DimStationRepository;
import com.reporting.service.DimStationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class DimStationServiceImpl implements DimStationService {

    @Autowired
    private DimStationRepository stationRepository;

    @Override
    public List<DimStationTO> findAllStations() throws DimStationNotFoundException {
        List<DimStation> stations = stationRepository.findAll();
        if (stations.isEmpty()) throw new DimStationNotFoundException("No stations found");
        return stations.stream().map(s -> new DimStationTO(s.getStationId(), s.getStationName(), s.getStationCode(), s.getLocation())).toList();
    }

    @Override
    public DimStationTO findStationById(Long id) throws DimStationNotFoundException {
        return stationRepository.findById(id)
                .map(s -> new DimStationTO(s.getStationId(), s.getStationName(), s.getStationCode(), s.getLocation()))
                .orElseThrow(() -> new DimStationNotFoundException("Station not found with id: " + id));
    }

    @Override
    public DimStationTO findByCode(String code) throws DimStationNotFoundException {
        return stationRepository.findByStationCode(code)
                .map(s -> new DimStationTO(s.getStationId(), s.getStationName(), s.getStationCode(), s.getLocation()))
                .orElseThrow(() -> new DimStationNotFoundException("Station not found with code: " + code));
    }

    @Override
    @Transactional
    public Long save(DimStationRequest request) {
        DimStation station = new DimStation();
        station.setStationName(request.getStationName());
        station.setStationCode(request.getStationCode());
        station.setLocation(request.getLocation());
        return stationRepository.save(station).getStationId();
    }

    @Override
    @Transactional
    public Long update(Long id, DimStationUpdateRequest request) throws DimStationNotFoundException {
        DimStation station = stationRepository.findById(id)
                .orElseThrow(() -> new DimStationNotFoundException("Station not found with id: " + id));

        if (request.getStationName() != null) station.setStationName(request.getStationName());
        if (request.getStationCode() != null) station.setStationCode(request.getStationCode());
        if (request.getLocation() != null) station.setLocation(request.getLocation());

        return stationRepository.save(station).getStationId();
    }

    @Override
    @Transactional
    public String deleteById(Long id) throws DimStationNotFoundException {
        if (!stationRepository.existsById(id)) {
            throw new DimStationNotFoundException("Station not found with id: " + id);
        }
        stationRepository.deleteById(id);
        return "Station deleted successfully";
    }
}