package com.flipkart.notificationservice.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @KafkaListener(topics = "notification-topic", groupId = "notification-group")
    public void onPaymentNotification(String message) {
        System.out.println("Notification Service received: " + message);
        // Swap this for a real email/SMS/push send later
    }

    @KafkaListener(topics = "failed-transaction-topic", groupId = "notification-group")
    public void onFailedTransaction(String message) {
        System.out.println("Notification Service ALERT (failed transaction): " + message);
    }
}