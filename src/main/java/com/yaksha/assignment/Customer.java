package com.yaksha.assignment;

public class Customer {

	private String customerId;
	private String name;

	// Default constructor
	public Customer() {
	}

	// Setter injection for customerId
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	// Setter injection for name
	public void setName(String name) {
		this.name = name;
	}

	// Getters
	public String getCustomerId() {
		return customerId;
	}

	public String getName() {
		return name;
	}
}
