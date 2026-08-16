package com.flipkart.deliveryservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "deliveries")
public class Delivery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long orderId;

	private String address;

	// CREATED -> ASSIGNED -> OUT_FOR_DELIVERY -> DELIVERED / FAILED
	private String status;

	private LocalDateTime createdAt;

	private LocalDateTime deliveredAt;

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public Long getOrderId() { return orderId; }
	public void setOrderId(Long orderId) { this.orderId = orderId; }
	public String getAddress() { return address; }
	public void setAddress(String address) { this.address = address; }
	public String getStatus() { return status; }
	public void setStatus(String status) { this.status = status; }
	public LocalDateTime getCreatedAt() { return createdAt; }
	public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
	public LocalDateTime getDeliveredAt() { return deliveredAt; }
	public void setDeliveredAt(LocalDateTime deliveredAt) { this.deliveredAt = deliveredAt; }
}