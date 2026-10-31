package com.reporting.service;

import com.reporting.exception.DimUserNotFoundException;
import com.reporting.exception.UserNotFoundException;
import com.reporting.model.DimUserRequest;
import com.reporting.model.DimUserTO;
import com.reporting.model.DimUserUpdateRequest;
import com.reporting.model.UserTo;

import java.time.LocalDate;
import java.util.List;

public interface UserReportingService {
    List<DimUserTO> findAllUsers() throws DimUserNotFoundException;
    DimUserTO findUserById(Long id) throws DimUserNotFoundException;
    List<DimUserTO> findUserByName(String name) throws DimUserNotFoundException;
    String deleteUserById(Long id) throws DimUserNotFoundException;
    String deleteUserByName(String name) throws DimUserNotFoundException;
    Long save(DimUserRequest userRequest) throws DimUserNotFoundException;
    Long update(Long userId, DimUserUpdateRequest userUpdateRequest) throws DimUserNotFoundException;
    List<UserTo> getAllUsers(LocalDate startDate, LocalDate endDate) throws UserNotFoundException;
}
