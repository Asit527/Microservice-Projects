package com.flipkart.deliveryservice.service;

import com.flipkart.deliveryservice.entity.Delivery;
import com.flipkart.deliveryservice.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    public Delivery scheduleDelivery(Long orderId, String address) {
        Delivery delivery = new Delivery();
        delivery.setOrderId(orderId);
        delivery.setAddress(address);
        delivery.setStatus("CREATED");
        delivery.setCreatedAt(LocalDateTime.now());
        return deliveryRepository.save(delivery);
    }

    public Delivery updateStatus(Long deliveryId, String status) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new IllegalArgumentException("Delivery not found: " + deliveryId));
        delivery.setStatus(status);
        if ("DELIVERED".equals(status)) {
            delivery.setDeliveredAt(LocalDateTime.now());
        }
        return deliveryRepository.save(delivery);
    }

    public List<Delivery> getByOrderId(Long orderId) {
        return deliveryRepository.findByOrderId(orderId);
    }

    public List<Delivery> getAll() {
        return deliveryRepository.findAll();
    }
}