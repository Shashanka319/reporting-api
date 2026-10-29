package com.reporting.repository;

import com.reporting.entity.FactWalletAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactWalletAuditRepository extends JpaRepository<FactWalletAudit, Long> {
    List<FactWalletAudit> findByMetroCardCardId(Long cardId);
    List<FactWalletAudit> findByTransactionType(String transactionType);
}