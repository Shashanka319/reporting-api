package com.reporting.repository;

import com.reporting.entity.FactPaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactPaymentTransactionRepository extends JpaRepository<FactPaymentTransaction, Long> {
    List<FactPaymentTransaction> findByMetroCardCardId(Long cardId);
    List<FactPaymentTransaction> findByStatus(String status);
}