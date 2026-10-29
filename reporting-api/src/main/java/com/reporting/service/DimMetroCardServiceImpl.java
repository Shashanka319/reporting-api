package com.reporting.service;

import com.reporting.entity.DimMetroCard;
import com.reporting.entity.DimUser;
import com.reporting.exception.DimMetroCardNotFoundException;
import com.reporting.exception.DimUserNotFoundException;
import com.reporting.model.DimMetroCardRequest;
import com.reporting.model.DimMetroCardTO;
import com.reporting.model.DimMetroCardUpdateRequest;
import com.reporting.repository.DimMetroCardRepository;
import com.reporting.repository.DimUserRepository;
import com.reporting.service.DimMetroCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class DimMetroCardServiceImpl implements DimMetroCardService {

    @Autowired
    private DimMetroCardRepository cardRepository;

    @Autowired
    private DimUserRepository userRepository;

    @Override
    public List<DimMetroCardTO> findAllCards() throws DimMetroCardNotFoundException {
        List<DimMetroCard> cards = cardRepository.findAll();
        if (cards.isEmpty()) throw new DimMetroCardNotFoundException("No cards found");
        return cards.stream().map(this::mapToTO).toList();
    }

    @Override
    public DimMetroCardTO findCardById(Long cardId) throws DimMetroCardNotFoundException {
        return cardRepository.findById(cardId)
                .map(this::mapToTO)
                .orElseThrow(() -> new DimMetroCardNotFoundException("Card not found with id: " + cardId));
    }

    @Override
    public DimMetroCardTO findByCardNumber(String cardNumber) throws DimMetroCardNotFoundException {
        return cardRepository.findByCardNumber(cardNumber)
                .map(this::mapToTO)
                .orElseThrow(() -> new DimMetroCardNotFoundException("Card not found with number: " + cardNumber));
    }

    @Override
    public List<DimMetroCardTO> findByUserId(Long userId) throws DimMetroCardNotFoundException {
        List<DimMetroCard> cards = cardRepository.findByUserUserId(userId);
        if (cards.isEmpty()) throw new DimMetroCardNotFoundException("No cards found for user id: " + userId);
        return cards.stream().map(this::mapToTO).toList();
    }

    @Override
    @Transactional
    public Long save(DimMetroCardRequest request) throws Exception {
        DimUser user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new DimUserNotFoundException("User not found with id: " + request.getUserId()));

        DimMetroCard card = new DimMetroCard();
        card.setCardNumber(request.getCardNumber());
        card.setBalance(request.getBalance());
        card.setStatus(request.getStatus() != null ? request.getStatus() : "ACTIVE");
        card.setIssuedDate(request.getIssuedDate() != null ? request.getIssuedDate() : LocalDateTime.now());
        card.setUser(user);

        return cardRepository.save(card).getCardId();
    }

    @Override
    @Transactional
    public Long update(Long cardId, DimMetroCardUpdateRequest request) throws DimMetroCardNotFoundException {
        DimMetroCard card = cardRepository.findById(cardId)
                .orElseThrow(() -> new DimMetroCardNotFoundException("Card not found with id: " + cardId));

        if (request.getBalance() != null) card.setBalance(request.getBalance());
        if (request.getStatus() != null) card.setStatus(request.getStatus());

        return cardRepository.save(card).getCardId();
    }

    @Override
    @Transactional
    public String deleteCardById(Long cardId) throws DimMetroCardNotFoundException {
        if (!cardRepository.existsById(cardId)) {
            throw new DimMetroCardNotFoundException("Card not found with id: " + cardId);
        }
        cardRepository.deleteById(cardId);
        return "Metro card deleted successfully";
    }

    private DimMetroCardTO mapToTO(DimMetroCard card) {
        return new DimMetroCardTO(
                card.getCardId(),
                card.getCardNumber(),
                card.getBalance(),
                card.getStatus(),
                card.getUser() != null ? card.getUser().getUserId() : null,
                card.getIssuedDate()
        );
    }
}