package com.yaksha.assignment;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ECommerceApplication {

	public static void main(String[] args) {
		// Load the Spring context from the XML configuration file
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		// Retrieve the beans from the Spring container
		Product product1 = context.getBean("product1", Product.class);
		Product product2 = context.getBean("product2", Product.class);
		Order order1 = context.getBean("order1", Order.class);
		Customer customer1 = context.getBean("customer1", Customer.class);

		// Display product, order, and customer details
		System.out.println("Product 1: " + product1.getName() + " priced at " + product1.getPrice());
		System.out.println("Product 2: " + product2.getName() + " priced at " + product2.getPrice());
		System.out.println("Order ID: " + order1.getOrderId() + ", Total Amount: " + order1.getTotalAmount());
		System.out.println("Customer ID: " + customer1.getCustomerId() + ", Name: " + customer1.getName());

		// Simulate adding products to the order
		order1.setProductList(List.of(product1, product2)); // Setting products to the order
	}
}
