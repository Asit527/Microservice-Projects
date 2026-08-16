package com.flipkart.paymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PaymentController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String NOTIFICATION_TOPIC = "notification-topic";

    @PostMapping("/payments")
    public String processPayment(@RequestBody Map<String, Object> order) {

        // Simulated payment processing - always succeeds for now.
        // Swap this for a real payment gateway (Stripe/Razorpay) call later.
        String status = "SUCCESS";

        String message = "Payment " + status + " for Order ID: " + order.get("id");
        kafkaTemplate.send(NOTIFICATION_TOPIC, message);

        System.out.println("Payment processed: " + message);

        return status;
    }
}