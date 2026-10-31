package com.reporting.service;

import com.reporting.entity.DimUser;
import com.reporting.exception.DimUserNotFoundException;
import com.reporting.model.DimMetroCardTO;
import com.reporting.model.DimUserRequest;
import com.reporting.model.DimUserTO;
import com.reporting.model.DimUserUpdateRequest;
import com.reporting.repository.DimUserRepository;
import com.reporting.service.DimUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class DimUserServiceImpl implements DimUserService {

    @Autowired
    private DimUserRepository userRepository;

    @Override
    public List<DimUserTO> findAllUsers() throws DimUserNotFoundException {
        log.info("Inside DimUserServiceImpl.findAllUsers");
        List<DimUser> users = userRepository.findAll();
        if (users.isEmpty()) {
            log.error("No users found");
            throw new DimUserNotFoundException("Users are empty");
        }
        return users.stream().map(this::mapToTO).toList();
    }

    @Override
    public DimUserTO findUserById(Long id) throws DimUserNotFoundException {
        log.info("Inside DimUserServiceImpl.findUserById: {}", id);
        DimUser user = userRepository.findById(id)
                .orElseThrow(() -> new DimUserNotFoundException("User not found with id: " + id));
        return mapToTO(user);
    }

    @Override
    public List<DimUserTO> findUserByName(String name) throws DimUserNotFoundException {
        log.info("Inside DimUserServiceImpl.findUserByName: {}", name);
        List<DimUser> users = userRepository.findByFullName(name);
        if (users.isEmpty()) {
            throw new DimUserNotFoundException("No users found with name: " + name);
        }
        return users.stream().map(this::mapToTO).toList();
    }

    @Override
    @Transactional
    public String deleteUserById(Long id) throws DimUserNotFoundException {
        log.info("Inside DimUserServiceImpl.deleteUserById: {}", id);
        if (!userRepository.existsById(id)) {
            throw new DimUserNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
        return "User details deleted successfully";
    }

    @Override
    @Transactional
    public String deleteUserByName(String name) throws DimUserNotFoundException {
        log.info("Inside DimUserServiceImpl.deleteUserByName: {}", name);
        List<DimUser> users = userRepository.findByFullName(name);
        if (users.isEmpty()) {
            throw new DimUserNotFoundException("User not found with name: " + name);
        }
        userRepository.deleteByFullName(name);
        return "User data deleted successfully";
    }

    @Override
    @Transactional
    public Long save(DimUserRequest request) throws DimUserNotFoundException {
        log.info("Inside DimUserServiceImpl.save: {}", request);
        DimUser user = new DimUser();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPasswordHash(request.getPasswordHash());
        user.setRole(request.getRole() != null ? request.getRole() : "PASSENGER");
        user.setCreatedAt(request.getCreatedAt() != null ? request.getCreatedAt() : LocalDateTime.now());

        DimUser saved = userRepository.save(user);
        return saved.getUserId();
    }

    @Override
    @Transactional
    public Long update(Long userId, DimUserUpdateRequest request) throws DimUserNotFoundException {
        log.info("Inside DimUserServiceImpl.update: {}, {}", userId, request);
        DimUser user = userRepository.findById(userId)
                .orElseThrow(() -> new DimUserNotFoundException("User not found with id: " + userId));

        if (request.getFullName() != null) user.setFullName(request.getFullName());
        if (request.getEmail() != null) user.setEmail(request.getEmail());
        if (request.getPhoneNumber() != null) user.setPhoneNumber(request.getPhoneNumber());
        if (request.getPasswordHash() != null) user.setPasswordHash(request.getPasswordHash());
        if (request.getRole() != null) user.setRole(request.getRole());

        DimUser updated = userRepository.save(user);
        return updated.getUserId();
    }

    private DimUserTO mapToTO(DimUser user) {
        DimUserTO to = new DimUserTO();
        to.setUserId(user.getUserId());
        to.setFullName(user.getFullName());
        to.setEmail(user.getEmail());
        to.setPhoneNumber(user.getPhoneNumber());
        to.setPasswordHash(user.getPasswordHash());
        to.setRole(user.getRole());
        to.setCreatedAt(user.getCreatedAt());

        if (user.getMetroCards() != null) {
            Set<DimMetroCardTO> cards = user.getMetroCards().stream().map(c -> {
                DimMetroCardTO cardTO = new DimMetroCardTO();
                cardTO.setCardId(c.getCardId());
                cardTO.setCardNumber(c.getCardNumber());
                cardTO.setBalance(c.getBalance() != null ? c.getBalance() : BigDecimal.ZERO);
                cardTO.setStatus(c.getStatus());
                cardTO.setUserId(user.getUserId());
                cardTO.setIssuedDate(c.getIssuedDate());
                return cardTO;
            }).collect(Collectors.toSet());
            to.setMetroCards(cards);
        }
        return to;
    }
}