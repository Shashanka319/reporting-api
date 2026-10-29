package com.reporting.service;

import com.reporting.exception.FactWalletAuditNotFoundException;
import com.reporting.model.FactWalletAuditRequest;
import com.reporting.model.FactWalletAuditTO;
import com.reporting.model.FactWalletAuditUpdateRequest;

import java.util.List;

public interface FactWalletAuditService {
    List<FactWalletAuditTO> findAllAudits() throws FactWalletAuditNotFoundException;
    FactWalletAuditTO findAuditById(Long id) throws FactWalletAuditNotFoundException;
    List<FactWalletAuditTO> findByCardId(Long cardId) throws FactWalletAuditNotFoundException;
    Long save(FactWalletAuditRequest request) throws Exception;
    Long update(Long txnId, FactWalletAuditUpdateRequest request) throws FactWalletAuditNotFoundException;
    String deleteById(Long txnId) throws FactWalletAuditNotFoundException;
}