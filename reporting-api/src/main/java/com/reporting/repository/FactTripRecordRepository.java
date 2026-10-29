package com.reporting.repository;

import com.reporting.entity.FactTripRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactTripRecordRepository extends JpaRepository<FactTripRecord, Long> {
    List<FactTripRecord> findByMetroCardCardId(Long cardId);
}