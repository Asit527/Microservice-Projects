package com.flipkart.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.flipkart.orderservice.entity.Order;

@FeignClient(name = "payment-service")
public interface PaymentClient {

    @PostMapping("/payments")
    String processPayment(@RequestBody Order order);
}