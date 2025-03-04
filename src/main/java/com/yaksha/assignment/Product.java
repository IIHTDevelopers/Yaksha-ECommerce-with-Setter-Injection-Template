package com.yaksha.assignment;

public class Product {

    private String name;
    private double price;

    // Default constructor
    public Product() {
    }

    // Setter injection for name
    public void setName(String name) {
        this.name = name;
    }

    // Setter injection for price
    public void setPrice(double price) {
        this.price = price;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
