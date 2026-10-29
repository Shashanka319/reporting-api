package com.reporting.service;

import com.reporting.exception.DimMetroCardNotFoundException;
import com.reporting.model.DimMetroCardRequest;
import com.reporting.model.DimMetroCardTO;
import com.reporting.model.DimMetroCardUpdateRequest;

import java.util.List;

public interface DimMetroCardService {
    List<DimMetroCardTO> findAllCards() throws DimMetroCardNotFoundException;
    DimMetroCardTO findCardById(Long cardId) throws DimMetroCardNotFoundException;
    DimMetroCardTO findByCardNumber(String cardNumber) throws DimMetroCardNotFoundException;
    List<DimMetroCardTO> findByUserId(Long userId) throws DimMetroCardNotFoundException;
    Long save(DimMetroCardRequest request) throws Exception;
    Long update(Long cardId, DimMetroCardUpdateRequest request) throws DimMetroCardNotFoundException;
    String deleteCardById(Long cardId) throws DimMetroCardNotFoundException;
}