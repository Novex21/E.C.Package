package com.ecommerce.orders;

import com.ecommerce.Product;
import com.ecommerce.Customer;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private String orderID;
    private Customer customer;
    private Map<Product, Integer> products;
    private double orderTotal;
    private OrderStatus status;
    private final LocalDateTime orderDate;
    private String shippingAddress;

    // Enum for order status
    public enum OrderStatus {
        PENDING, CONFIRMED, PROCESSING, SHIPPED, DELIVERED, CANCELLED
    }

    // Default constructor
    public Order() {
        this.products = new HashMap<>();
        this.status = OrderStatus.PENDING;
        this.orderDate = LocalDateTime.now();
    }

    // Parameterized constructor
    public Order(String orderID, Customer customer, Map<Product, Integer> products, String shippingAddress) {
        this.orderID = orderID;
        this.customer = customer;
        this.products = new HashMap<>(products); // Create copy
        this.shippingAddress = shippingAddress;
        this.status = OrderStatus.PENDING;
        this.orderDate = LocalDateTime.now();
        this.orderTotal = calculateTotal();
    }

    // Getters
    public String getOrderID() {
        return orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Map<Product, Integer> getProducts() {
        return new HashMap<>(products); // Return copy for encapsulation
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    // Setters
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    // Business methods
    private double calculateTotal() {
        double total = 0.0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public void updateOrderStatus(OrderStatus newStatus) {
        this.status = newStatus;
        System.out.println("Order " + orderID + " status updated to: " + newStatus);
    }

    public boolean addProduct(Product product, int quantity) {
        if (product == null || quantity <= 0) {
            return false;
        }

        products.put(product, products.getOrDefault(product, 0) + quantity);
        orderTotal = calculateTotal();
        return true;
    }

    public boolean removeProduct(Product product) {
        if (product == null || !products.containsKey(product)) {
            return false;
        }

        products.remove(product);
        orderTotal = calculateTotal();
        return true;
    }

    public String generateOrderSummary() {
        StringBuilder summary = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        summary.append("\n=== ORDER SUMMARY ===\n");
        summary.append("Order ID: ").append(orderID).append("\n");
        summary.append("Customer: ").append(customer.getName()).append("\n");
        summary.append("Order Date: ").append(orderDate.format(formatter)).append("\n");
        summary.append("Status: ").append(status).append("\n");
        summary.append("Shipping Address: ").append(shippingAddress).append("\n");
        summary.append("\nProducts Ordered:\n");

        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            double subtotal = product.getPrice() * quantity;
            summary.append(String.format("- %-20s | Qty: %d | Price: $%.2f | Subtotal: $%.2f%n",
                    product.getName(), quantity, product.getPrice(), subtotal));
        }

        summary.append(String.format("\nOrder Total: $%.2f", orderTotal));
        summary.append("\n=====================\n");

        return summary.toString();
    }

    public boolean processOrder() {
        if (status != OrderStatus.PENDING) {
            System.out.println("Order cannot be processed. Current status: " + status);
            return false;
        }

        // Check stock availability and update inventory
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            if (product.getStockQuantity() < quantity) {
                System.out.println("Insufficient stock for product: " + product.getName());
                return false;
            }
        }

        // Update stock quantities
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            product.decreaseStock(quantity);
        }

        updateOrderStatus(OrderStatus.CONFIRMED);
        return true;
    }

    // Simple display method
    public void displayOrder() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.printf("Order ID: %s, Customer: %s, Total: $%.2f, Status: %s, Date: %s%n",
                orderID, customer.getName(), orderTotal, status, orderDate.format(formatter));
    }
}
