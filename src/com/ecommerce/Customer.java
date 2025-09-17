package com.ecommerce;
import java.util.*;
import com.ecommerce.orders.Order;


public class Customer {
    private String customerID;
    private String name;
    private String email;
    private Map<Product, Integer> shoppingCart;
    private List<Order> orderHistory;

    // Default constructor
    public Customer() {
        this.shoppingCart = new HashMap<>();
        this.orderHistory = new ArrayList<>();
    }

    // Parameterized constructor
    public Customer(String customerID, String name, String email) {
        this.customerID = customerID;
        this.name = name;
        this.email = email;
        this.shoppingCart = new HashMap<>();
        this.orderHistory = new ArrayList<>();
    }

    // Getters
    public String getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Map<Product, Integer> getShoppingCart() {
        return new HashMap<>(shoppingCart); // Return copy for encapsulation
    }

    public List<Order> getOrderHistory() {
        return new ArrayList<>(orderHistory); // Return copy for encapsulation
    }

    // Setters
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Shopping cart methods
    public boolean addProductToCart(Product product, int quantity) {
        if (product == null || quantity <= 0 || !product.isInStock()) {
            return false;
        }

        if (product.getStockQuantity() < quantity) {
            System.out.println("Insufficient stock for product: " + product.getName());
            return false;
        }

        shoppingCart.put(product, shoppingCart.getOrDefault(product, 0) + quantity);
        System.out.println(quantity + " " + product.getName() + "(s) added to cart.");
        return true;
    }

    public boolean removeProductFromCart(Product product, int quantity) {
        if (product == null || quantity <= 0 || !shoppingCart.containsKey(product)) {
            return false;
        }

        int currentQuantity = shoppingCart.get(product);
        if (quantity >= currentQuantity) {
            shoppingCart.remove(product);
            System.out.println(product.getName() + " removed from cart completely.");
        } else {
            shoppingCart.put(product, currentQuantity - quantity);
            System.out.println(quantity + " " + product.getName() + "(s) removed from cart.");
        }
        return true;
    }

    public void clearCart() {
        shoppingCart.clear();
        System.out.println("Shopping cart cleared.");
    }

    public double calculateCartTotal() {
        double total = 0.0;
        for (Map.Entry<Product, Integer> entry : shoppingCart.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public void displayCart() {
        if (shoppingCart.isEmpty()) {
            System.out.println("Shopping cart is empty.");
            return;
        }

        System.out.println("\n--- Shopping Cart for " + name + " ---");
        for (Map.Entry<Product, Integer> entry : shoppingCart.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            double subtotal = product.getPrice() * quantity;
            System.out.printf("%-20s | Qty: %d | Price: $%.2f | Subtotal: $%.2f%n",
                    product.getName(), quantity, product.getPrice(), subtotal);
        }
        System.out.printf("Total: $%.2f%n", calculateCartTotal());
    }

    // Order methods
    public void addOrderToHistory(Order order) {
        if (order != null) {
            orderHistory.add(order);
        }
    }

    public void displayOrderHistory() {
        if (orderHistory.isEmpty()) {
            System.out.println("No order history available.");
            return;
        }

        System.out.println("\n--- Order History for " + name + " ---");
        for (Order order : orderHistory) {
            System.out.println(order);
        }
    }

    // Simple display method
    public void displayCustomer() {
        System.out.printf("Customer ID: %s, Name: %s, Email: %s, Cart Items: %d%n",
                customerID, name, email, shoppingCart.size());
    }
}
