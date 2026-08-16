package com.flipkart.orderservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String itemName;
	private int quantity;
	private double price;
	private String status;
	private String deliveryAddress;

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getItemName() { return itemName; }
	public void setItemName(String itemName) { this.itemName = itemName; }
	public int getQuantity() { return quantity; }
	public void setQuantity(int quantity) { this.quantity = quantity; }
	public double getPrice() { return price; }
	public void setPrice(double price) { this.price = price; }
	public String getStatus() { return status; }
	public void setStatus(String status) { this.status = status; }
	public String getDeliveryAddress() { return deliveryAddress; }
	public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }
}