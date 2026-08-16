package com.flipkart.deliveryservice.consumer;

import com.flipkart.deliveryservice.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderCompletedConsumer {

    @Autowired
    private DeliveryService deliveryService;

    @KafkaListener(topics = "order-completed-topic", groupId = "delivery-group")
    public void onOrderCompleted(String message) {
        System.out.println("Delivery Service received: " + message);

        // Order Service sends this as "orderId|address"
        String[] parts = message.split("\\|");
        if (parts.length == 2) {
            Long orderId = Long.parseLong(parts[0]);
            String address = parts[1];
            deliveryService.scheduleDelivery(orderId, address);
        }
    }
}