package com.flipkart.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.flipkart.orderservice.client.PaymentClient;
import com.flipkart.orderservice.entity.Order;
import com.flipkart.orderservice.repository.OrderRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentClient paymentClient;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String FAILED_TRANSACTION_TOPIC = "failed-transaction-topic";
    private static final String ORDER_COMPLETED_TOPIC = "order-completed-topic";

    @CircuitBreaker(name = "paymentService", fallbackMethod = "paymentFallback")
    public Order createOrder(Order order) {

        order.setStatus("PENDING");
        Order savedOrder = orderRepository.save(order);

        String paymentResponse = paymentClient.processPayment(savedOrder);

        if ("SUCCESS".equals(paymentResponse)) {
            savedOrder.setStatus("COMPLETED");
            sendOrderCompletedToKafka(savedOrder);
        } else {
            savedOrder.setStatus("FAILED");
            sendFailedTransactionToKafka(savedOrder);
        }

        return orderRepository.save(savedOrder);
    }

    public Order paymentFallback(Order order, Throwable throwable) {
        System.out.println("Payment Service unavailable. Circuit breaker fallback executed.");

        order.setStatus("FAILED");
        Order failedOrder = orderRepository.save(order);
        sendFailedTransactionToKafka(failedOrder);

        return failedOrder;
    }

    private void sendFailedTransactionToKafka(Order order) {
        String message = "FAILED TRANSACTION | Order ID: " + order.getId()
                + ", Item: " + order.getItemName()
                + ", Quantity: " + order.getQuantity()
                + ", Price: " + order.getPrice();

        kafkaTemplate.send(FAILED_TRANSACTION_TOPIC, String.valueOf(order.getId()), message);
        System.out.println("Failed transaction sent to Kafka: " + message);
    }

    private void sendOrderCompletedToKafka(Order order) {
        String address = order.getDeliveryAddress() != null ? order.getDeliveryAddress() : "UNKNOWN";
        String message = order.getId() + "|" + address;

        kafkaTemplate.send(ORDER_COMPLETED_TOPIC, String.valueOf(order.getId()), message);
        System.out.println("Order completed event sent to Kafka: " + message);
    }
}