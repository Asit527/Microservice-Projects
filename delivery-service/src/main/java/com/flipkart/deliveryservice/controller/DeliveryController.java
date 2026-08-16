package com.flipkart.deliveryservice.controller;

import com.flipkart.deliveryservice.entity.Delivery;
import com.flipkart.deliveryservice.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @PatchMapping("/{id}/status")
    public Delivery updateStatus(@PathVariable Long id, @RequestParam String status) {
        return deliveryService.updateStatus(id, status);
    }

    @GetMapping("/order/{orderId}")
    public List<Delivery> byOrder(@PathVariable Long orderId) {
        return deliveryService.getByOrderId(orderId);
    }

    @GetMapping
    public List<Delivery> all() {
        return deliveryService.getAll();
    }
}