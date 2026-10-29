package com.reporting.service;

import com.reporting.entity.DimRoute;
import com.reporting.exception.DimRouteNotFoundException;
import com.reporting.model.DimRouteRequest;
import com.reporting.model.DimRouteTO;
import com.reporting.model.DimRouteUpdateRequest;
import com.reporting.repository.DimRouteRepository;
import com.reporting.service.DimRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class DimRouteServiceImpl implements DimRouteService {

    @Autowired
    private DimRouteRepository routeRepository;

    @Override
    public List<DimRouteTO> findAllRoutes() throws DimRouteNotFoundException {
        List<DimRoute> routes = routeRepository.findAll();
        if (routes.isEmpty()) throw new DimRouteNotFoundException("No routes found");
        return routes.stream().map(r -> new DimRouteTO(r.getRouteId(), r.getRouteName())).toList();
    }

    @Override
    public DimRouteTO findRouteById(Long id) throws DimRouteNotFoundException {
        return routeRepository.findById(id)
                .map(r -> new DimRouteTO(r.getRouteId(), r.getRouteName()))
                .orElseThrow(() -> new DimRouteNotFoundException("Route not found with id: " + id));
    }

    @Override
    @Transactional
    public Long save(DimRouteRequest request) {
        DimRoute route = new DimRoute();
        route.setRouteName(request.getRouteName());
        return routeRepository.save(route).getRouteId();
    }

    @Override
    @Transactional
    public Long update(Long id, DimRouteUpdateRequest request) throws DimRouteNotFoundException {
        DimRoute route = routeRepository.findById(id)
                .orElseThrow(() -> new DimRouteNotFoundException("Route not found with id: " + id));
        if (request.getRouteName() != null) route.setRouteName(request.getRouteName());
        return routeRepository.save(route).getRouteId();
    }

    @Override
    @Transactional
    public String deleteById(Long id) throws DimRouteNotFoundException {
        if (!routeRepository.existsById(id)) {
            throw new DimRouteNotFoundException("Route not found with id: " + id);
        }
        routeRepository.deleteById(id);
        return "Route deleted successfully";
    }
}