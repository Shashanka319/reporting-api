package com.reporting.service;

import com.reporting.exception.FactPaymentTransactionNotFoundException;
import com.reporting.model.FactPaymentTransactionRequest;
import com.reporting.model.FactPaymentTransactionTO;
import com.reporting.model.FactPaymentTransactionUpdateRequest;

import java.util.List;

public interface FactPaymentTransactionService {
    List<FactPaymentTransactionTO> findAllPayments() throws FactPaymentTransactionNotFoundException;
    FactPaymentTransactionTO findPaymentById(Long id) throws FactPaymentTransactionNotFoundException;
    List<FactPaymentTransactionTO> findByCardId(Long cardId) throws FactPaymentTransactionNotFoundException;
    Long save(FactPaymentTransactionRequest request) throws Exception;
    Long update(Long paymentId, FactPaymentTransactionUpdateRequest request) throws FactPaymentTransactionNotFoundException;
    String deleteById(Long paymentId) throws FactPaymentTransactionNotFoundException;
}