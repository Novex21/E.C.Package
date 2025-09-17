package com.ecommerce;

public class Product {
    private String productID;
    private String name;
    private double price;
    private String description;
    private int stockQuantity;

    // Default constructor
    public Product() {}

    // Parameterized constructor
    public Product(String productID, String name, double price, String description, int stockQuantity) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.description = description;
        this.stockQuantity = stockQuantity;
    }

    // Getters
    public String getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    // Setters
    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        }
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity >= 0) {
            this.stockQuantity = stockQuantity;
        }
    }

    // Business methods
    public boolean isInStock() {
        return stockQuantity > 0;
    }

    public void decreaseStock(int quantity) {
        if (quantity > 0 && quantity <= stockQuantity) {
            stockQuantity -= quantity;
        }
    }

    public void increaseStock(int quantity) {
        if (quantity > 0) {
            stockQuantity += quantity;
        }
    }

    // Simple display method
    public void displayProduct() {
        System.out.printf("Product ID: %s, Name: %s, Price: $%.2f, Stock: %d%n",
                productID, name, price, stockQuantity);
    }
}
