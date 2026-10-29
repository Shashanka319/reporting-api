package com.reporting.service;

import com.reporting.entity.DimMetroCard;
import com.reporting.entity.FactPaymentTransaction;
import com.reporting.exception.DimMetroCardNotFoundException;
import com.reporting.exception.FactPaymentTransactionNotFoundException;
import com.reporting.model.FactPaymentTransactionRequest;
import com.reporting.model.FactPaymentTransactionTO;
import com.reporting.model.FactPaymentTransactionUpdateRequest;
import com.reporting.repository.DimMetroCardRepository;
import com.reporting.repository.FactPaymentTransactionRepository;
import com.reporting.service.FactPaymentTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class FactPaymentTransactionServiceImpl implements FactPaymentTransactionService {

    @Autowired
    private FactPaymentTransactionRepository paymentRepository;

    @Autowired
    private DimMetroCardRepository cardRepository;

    @Override
    public List<FactPaymentTransactionTO> findAllPayments() throws FactPaymentTransactionNotFoundException {
        List<FactPaymentTransaction> list = paymentRepository.findAll();
        if (list.isEmpty()) throw new FactPaymentTransactionNotFoundException("No payment records found");
        return list.stream().map(this::mapToTO).toList();
    }

    @Override
    public FactPaymentTransactionTO findPaymentById(Long id) throws FactPaymentTransactionNotFoundException {
        return paymentRepository.findById(id)
                .map(this::mapToTO)
                .orElseThrow(() -> new FactPaymentTransactionNotFoundException("Payment record not found: " + id));
    }

    @Override
    public List<FactPaymentTransactionTO> findByCardId(Long cardId) throws FactPaymentTransactionNotFoundException {
        List<FactPaymentTransaction> list = paymentRepository.findByMetroCardCardId(cardId);
        if (list.isEmpty()) throw new FactPaymentTransactionNotFoundException("No payments found for card: " + cardId);
        return list.stream().map(this::mapToTO).toList();
    }

    @Override
    @Transactional
    public Long save(FactPaymentTransactionRequest request) throws Exception {
        DimMetroCard card = cardRepository.findById(request.getCardId())
                .orElseThrow(() -> new DimMetroCardNotFoundException("Card not found: " + request.getCardId()));

        FactPaymentTransaction payment = new FactPaymentTransaction();
        payment.setMetroCard(card);
        payment.setAmount(request.getAmount());
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setStatus(request.getStatus() != null ? request.getStatus() : "SUCCESS");
        payment.setPaymentDate(request.getPaymentDate() != null ? request.getPaymentDate() : LocalDateTime.now());

        return paymentRepository.save(payment).getPaymentId();
    }

    @Override
    @Transactional
    public Long update(Long paymentId, FactPaymentTransactionUpdateRequest request) throws FactPaymentTransactionNotFoundException {
        FactPaymentTransaction payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new FactPaymentTransactionNotFoundException("Payment not found: " + paymentId));

        if (request.getPaymentMethod() != null) payment.setPaymentMethod(request.getPaymentMethod());
        if (request.getStatus() != null) payment.setStatus(request.getStatus());

        return paymentRepository.save(payment).getPaymentId();
    }

    @Override
    @Transactional
    public String deleteById(Long paymentId) throws FactPaymentTransactionNotFoundException {
        if (!paymentRepository.existsById(paymentId)) {
            throw new FactPaymentTransactionNotFoundException("Payment not found: " + paymentId);
        }
        paymentRepository.deleteById(paymentId);
        return "Payment transaction deleted successfully";
    }

    private FactPaymentTransactionTO mapToTO(FactPaymentTransaction payment) {
        return new FactPaymentTransactionTO(
                payment.getPaymentId(),
                payment.getMetroCard() != null ? payment.getMetroCard().getCardId() : null,
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getStatus(),
                payment.getPaymentDate()
        );
    }
}