package com.reporting.service;

import com.reporting.entity.DimMetroCard;
import com.reporting.entity.FactWalletAudit;
import com.reporting.exception.DimMetroCardNotFoundException;
import com.reporting.exception.FactWalletAuditNotFoundException;
import com.reporting.model.FactWalletAuditRequest;
import com.reporting.model.FactWalletAuditTO;
import com.reporting.model.FactWalletAuditUpdateRequest;
import com.reporting.repository.DimMetroCardRepository;
import com.reporting.repository.FactWalletAuditRepository;
import com.reporting.service.FactWalletAuditService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class FactWalletAuditServiceImpl implements FactWalletAuditService {

    @Autowired
    private FactWalletAuditRepository auditRepository;

    @Autowired
    private DimMetroCardRepository cardRepository;

    @Override
    public List<FactWalletAuditTO> findAllAudits() throws FactWalletAuditNotFoundException {
        List<FactWalletAudit> list = auditRepository.findAll();
        if (list.isEmpty()) throw new FactWalletAuditNotFoundException("No wallet audits found");
        return list.stream().map(this::mapToTO).toList();
    }

    @Override
    public FactWalletAuditTO findAuditById(Long id) throws FactWalletAuditNotFoundException {
        return auditRepository.findById(id)
                .map(this::mapToTO)
                .orElseThrow(() -> new FactWalletAuditNotFoundException("Audit record not found: " + id));
    }

    @Override
    public List<FactWalletAuditTO> findByCardId(Long cardId) throws FactWalletAuditNotFoundException {
        List<FactWalletAudit> list = auditRepository.findByMetroCardCardId(cardId);
        if (list.isEmpty()) throw new FactWalletAuditNotFoundException("No audits found for card: " + cardId);
        return list.stream().map(this::mapToTO).toList();
    }

    @Override
    @Transactional
    public Long save(FactWalletAuditRequest request) throws Exception {
        DimMetroCard card = cardRepository.findById(request.getCardId())
                .orElseThrow(() -> new DimMetroCardNotFoundException("Card not found: " + request.getCardId()));

        FactWalletAudit audit = new FactWalletAudit();
        audit.setMetroCard(card);
        audit.setAmount(request.getAmount());
        audit.setTransactionType(request.getTxnType());
        audit.setTransactionDate(request.getTxnDate() != null ? request.getTxnDate() : LocalDateTime.now());

        return auditRepository.save(audit).getTransactionId();
    }

    @Override
    @Transactional
    public Long update(Long txnId, FactWalletAuditUpdateRequest request) throws FactWalletAuditNotFoundException {
        FactWalletAudit audit = auditRepository.findById(txnId)
                .orElseThrow(() -> new FactWalletAuditNotFoundException("Audit not found: " + txnId));

        if (request.getAmount() != null) audit.setAmount(request.getAmount());
        if (request.getTxnType() != null) audit.setTransactionType(request.getTxnType());

        return auditRepository.save(audit).getTransactionId();
    }

    @Override
    @Transactional
    public String deleteById(Long txnId) throws FactWalletAuditNotFoundException {
        if (!auditRepository.existsById(txnId)) {
            throw new FactWalletAuditNotFoundException("Audit not found: " + txnId);
        }
        auditRepository.deleteById(txnId);
        return "Wallet audit record deleted successfully";
    }

    private FactWalletAuditTO mapToTO(FactWalletAudit audit) {
        return new FactWalletAuditTO(
                audit.getTransactionId(),
                audit.getMetroCard() != null ? audit.getMetroCard().getCardId() : null,
                audit.getAmount(),
                audit.getTransactionType(),
                audit.getTransactionDate()
        );
    }
}