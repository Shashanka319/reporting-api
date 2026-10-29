package com.reporting.repository;

import com.reporting.entity.DimUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DimUserRepository extends JpaRepository<DimUser, Long> {
    List<DimUser> findByFullName(String fullName);
    Optional<DimUser> findByEmail(String email);

    @Modifying
    @Query("DELETE FROM DimUser u WHERE u.fullName = :fullName")
    void deleteByFullName(String fullName);
}