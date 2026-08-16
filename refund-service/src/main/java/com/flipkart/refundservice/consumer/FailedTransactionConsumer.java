package com.flipkart.refundservice.consumer;

import com.flipkart.refundservice.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class FailedTransactionConsumer {

    @Autowired
    private RefundService refundService;

    @KafkaListener(topics = "failed-transaction-topic", groupId = "refund-group")
    public void onFailedTransaction(String message) {
        System.out.println("Refund Service saw failed transaction: " + message);

        // Order Service sends: "FAILED TRANSACTION | Order ID: X, Item: Y, Quantity: Z, Price: P"
        try {
            Long orderId = Long.parseLong(message.split("Order ID: ")[1].split(",")[0].trim());
            double price = Double.parseDouble(message.split("Price: ")[1].trim());
            refundService.initiateRefund(orderId, price, "Automatic refund - payment failure");
        } catch (Exception e) {
            System.err.println("Could not parse failed-transaction message for refund: " + e.getMessage());
        }
    }
}