package com.reporting.repository;

import com.reporting.entity.DimMetroCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DimMetroCardRepository extends JpaRepository<DimMetroCard, Long> {
    Optional<DimMetroCard> findByCardNumber(String cardNumber);
    List<DimMetroCard> findByUserUserId(Long userId);
}