package com.reporting.service;

import com.reporting.exception.FactTripRecordNotFoundException;
import com.reporting.model.FactTripRecordRequest;
import com.reporting.model.FactTripRecordTO;
import com.reporting.model.FactTripRecordUpdateRequest;

import java.util.List;

public interface FactTripRecordService {
    List<FactTripRecordTO> findAllTrips() throws FactTripRecordNotFoundException;
    FactTripRecordTO findTripById(Long id) throws FactTripRecordNotFoundException;
    List<FactTripRecordTO> findTripsByCardId(Long cardId) throws FactTripRecordNotFoundException;
    Long save(FactTripRecordRequest request) throws Exception;
    Long update(Long tripId, FactTripRecordUpdateRequest request) throws FactTripRecordNotFoundException;
    String deleteById(Long tripId) throws FactTripRecordNotFoundException;
}