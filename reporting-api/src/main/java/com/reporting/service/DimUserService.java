package com.reporting.service;

import com.reporting.exception.DimUserNotFoundException;
import com.reporting.model.DimUserRequest;
import com.reporting.model.DimUserTO;
import com.reporting.model.DimUserUpdateRequest;

import java.util.List;

public interface DimUserService {
    List<DimUserTO> findAllUsers() throws DimUserNotFoundException;
    DimUserTO findUserById(Long id) throws DimUserNotFoundException;
    List<DimUserTO> findUserByName(String name) throws DimUserNotFoundException;
    String deleteUserById(Long id) throws DimUserNotFoundException;
    String deleteUserByName(String name) throws DimUserNotFoundException;
    Long save(DimUserRequest userRequest) throws DimUserNotFoundException;
    Long update(Long userId, DimUserUpdateRequest userUpdateRequest) throws DimUserNotFoundException;
}