package com.flipkart.refundservice.controller;

import com.flipkart.refundservice.entity.Refund;
import com.flipkart.refundservice.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/refunds")
public class RefundController {

    @Autowired
    private RefundService refundService;

    @PostMapping
    public Refund initiate(@RequestParam Long orderId, @RequestParam double amount, @RequestParam String reason) {
        return refundService.initiateRefund(orderId, amount, reason);
    }

    @GetMapping("/order/{orderId}")
    public List<Refund> byOrder(@PathVariable Long orderId) {
        return refundService.getByOrderId(orderId);
    }

    @GetMapping
    public List<Refund> all() {
        return refundService.getAll();
    }
}