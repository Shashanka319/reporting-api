package com.reporting.service;

import com.reporting.entity.DimMetroCard;
import com.reporting.entity.DimStation;
import com.reporting.entity.FactTripRecord;
import com.reporting.exception.DimMetroCardNotFoundException;
import com.reporting.exception.DimStationNotFoundException;
import com.reporting.exception.FactTripRecordNotFoundException;
import com.reporting.model.FactTripRecordRequest;
import com.reporting.model.FactTripRecordTO;
import com.reporting.model.FactTripRecordUpdateRequest;
import com.reporting.repository.DimMetroCardRepository;
import com.reporting.repository.DimStationRepository;
import com.reporting.repository.FactTripRecordRepository;
import com.reporting.service.FactTripRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class FactTripRecordServiceImpl implements FactTripRecordService {

    @Autowired
    private FactTripRecordRepository tripRepository;

    @Autowired
    private DimMetroCardRepository cardRepository;

    @Autowired
    private DimStationRepository stationRepository;

    @Override
    public List<FactTripRecordTO> findAllTrips() throws FactTripRecordNotFoundException {
        List<FactTripRecord> trips = tripRepository.findAll();
        if (trips.isEmpty()) throw new FactTripRecordNotFoundException("No trips found");
        return trips.stream().map(this::mapToTO).toList();
    }

    @Override
    public FactTripRecordTO findTripById(Long id) throws FactTripRecordNotFoundException {
        return tripRepository.findById(id)
                .map(this::mapToTO)
                .orElseThrow(() -> new FactTripRecordNotFoundException("Trip not found with id: " + id));
    }

    @Override
    public List<FactTripRecordTO> findTripsByCardId(Long cardId) throws FactTripRecordNotFoundException {
        List<FactTripRecord> trips = tripRepository.findByMetroCardCardId(cardId);
        if (trips.isEmpty()) throw new FactTripRecordNotFoundException("No trips found for card id: " + cardId);
        return trips.stream().map(this::mapToTO).toList();
    }

    @Override
    @Transactional
    public Long save(FactTripRecordRequest request) throws Exception {
        DimMetroCard card = cardRepository.findById(request.getCardId())
                .orElseThrow(() -> new DimMetroCardNotFoundException("Card not found: " + request.getCardId()));
        DimStation entry = stationRepository.findById(request.getEntryStationId())
                .orElseThrow(() -> new DimStationNotFoundException("Entry station not found: " + request.getEntryStationId()));

        FactTripRecord trip = new FactTripRecord();
        trip.setMetroCard(card);
        trip.setEntryStation(entry);
        trip.setStartTime(request.getStartTime() != null ? request.getStartTime() : LocalDateTime.now());
        trip.setFareAmount(request.getFareAmount());

        if (request.getExitStationId() != null) {
            DimStation exit = stationRepository.findById(request.getExitStationId())
                    .orElseThrow(() -> new DimStationNotFoundException("Exit station not found: " + request.getExitStationId()));
            trip.setExitStation(exit);
            trip.setEndTime(request.getEndTime());
        }

        return tripRepository.save(trip).getTripId();
    }

    @Override
    @Transactional
    public Long update(Long tripId, FactTripRecordUpdateRequest request) throws FactTripRecordNotFoundException {
        FactTripRecord trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new FactTripRecordNotFoundException("Trip not found with id: " + tripId));

        if (request.getExitStationId() != null) {
            stationRepository.findById(request.getExitStationId()).ifPresent(trip::setExitStation);
        }
        if (request.getEndTime() != null) trip.setEndTime(request.getEndTime());
        if (request.getFareAmount() != null) trip.setFareAmount(request.getFareAmount());

        return tripRepository.save(trip).getTripId();
    }

    @Override
    @Transactional
    public String deleteById(Long tripId) throws FactTripRecordNotFoundException {
        if (!tripRepository.existsById(tripId)) {
            throw new FactTripRecordNotFoundException("Trip not found with id: " + tripId);
        }
        tripRepository.deleteById(tripId);
        return "Trip record deleted successfully";
    }

    private FactTripRecordTO mapToTO(FactTripRecord trip) {
        return new FactTripRecordTO(
                trip.getTripId(),
                trip.getMetroCard() != null ? trip.getMetroCard().getCardId() : null,
                trip.getEntryStation() != null ? trip.getEntryStation().getStationId() : null,
                trip.getExitStation() != null ? trip.getExitStation().getStationId() : null,
                trip.getStartTime(),
                trip.getEndTime(),
                trip.getFareAmount()
        );
    }
}