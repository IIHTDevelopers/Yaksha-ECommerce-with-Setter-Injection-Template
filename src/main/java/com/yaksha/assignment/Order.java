package com.yaksha.assignment;

import java.util.List;

public class Order {

	private String orderId;
	private double totalAmount;
	private List<Product> productList;

	// Default constructor
	public Order() {
	}

	// Setter injection for orderId
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	// Setter injection for totalAmount
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	// Setter injection for productList
	public void setProductList(List<Product> productList) {
		this.productList = productList;
		calculateTotalAmount(); // Recalculate total amount when products are set
	}

	// Calculate total amount based on the products in the order
	private void calculateTotalAmount() {
		for (Product product : productList) {
			totalAmount += product.getPrice();
		}
	}

	// Getters
	public String getOrderId() {
		return orderId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}
}
