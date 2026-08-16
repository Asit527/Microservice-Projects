package com.flipkart.refundservice.service;

import com.flipkart.refundservice.entity.Refund;
import com.flipkart.refundservice.repository.RefundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RefundService {

    @Autowired
    private RefundRepository refundRepository;

    public Refund initiateRefund(Long orderId, double amount, String reason) {
        Refund refund = new Refund();
        refund.setOrderId(orderId);
        refund.setAmount(amount);
        refund.setReason(reason);
        refund.setStatus("INITIATED");
        refund.setCreatedAt(LocalDateTime.now());

        Refund saved = refundRepository.save(refund);

        // TODO: call a real payment gateway's refund API here.
        // Marking COMPLETED immediately for now, to keep the saga simple.
        saved.setStatus("COMPLETED");
        return refundRepository.save(saved);
    }

    public List<Refund> getByOrderId(Long orderId) {
        return refundRepository.findByOrderId(orderId);
    }

    public List<Refund> getAll() {
        return refundRepository.findAll();
    }
}