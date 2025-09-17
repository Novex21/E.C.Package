import com.ecommerce.Product;
import com.ecommerce.Customer;
import com.ecommerce.orders.Order;
import com.ecommerce.orders.Order.OrderStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Product> productCatalog = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    private static List<Order> allOrders = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Welcome to Java E-Commerce System ===\n");

        // Initialize sample data
        initializeSampleData();

        // Demonstrate the system functionality
        demonstrateSystem();

        // Interactive menu (optional)
        // runInteractiveMenu();

        scanner.close();

    }

    private static void initializeSampleData() {
        // Create sample products
        Product laptop = new Product("P001", "Gaming Laptop", 1299.99, "High-performance gaming laptop", 10);
        Product mouse = new Product("P002", "Wireless Mouse", 29.99, "Ergonomic wireless mouse", 50);
        Product keyboard = new Product("P003", "Mechanical Keyboard", 89.99, "RGB mechanical keyboard", 25);
        Product monitor = new Product("P004", "4K Monitor", 349.99, "27-inch 4K monitor", 15);
        Product headphones = new Product("P005", "Gaming Headset", 79.99, "Noise-canceling gaming headset", 30);

        productCatalog.add(laptop);
        productCatalog.add(mouse);
        productCatalog.add(keyboard);
        productCatalog.add(monitor);
        productCatalog.add(headphones);

        // Create sample customers
        Customer alice = new Customer("C001", "Alice Johnson", "alice@email.com");
        Customer bob = new Customer("C002", "Bob Smith", "bob@email.com");
        Customer carol = new Customer("C003", "Carol Davis", "carol@email.com");

        customers.add(alice);
        customers.add(bob);
        customers.add(carol);

        System.out.println("Sample data initialized successfully!");
        System.out.println("Products: " + productCatalog.size());
        System.out.println("Customers: " + customers.size() + "\n");
    }

    private static void demonstrateSystem() {
        System.out.println("=== SYSTEM DEMONSTRATION ===\n");

        // Get sample customer and products
        Customer alice = customers.get(0);
        Product laptop = productCatalog.get(0);
        Product mouse = productCatalog.get(1);
        Product keyboard = productCatalog.get(2);

        // 1. Display available products
        System.out.println("1. BROWSING PRODUCTS:");
        displayProductCatalog();

        // 2. Customer adds products to cart
        System.out.println("\n2. ADDING PRODUCTS TO CART:");
        alice.addProductToCart(laptop, 1);
        alice.addProductToCart(mouse, 2);
        alice.addProductToCart(keyboard, 1);

        // 3. Display shopping cart
        System.out.println("\n3. SHOPPING CART:");
        alice.displayCart();

        // 4. Modify cart (remove some items)
        System.out.println("\n4. MODIFYING CART:");
        alice.removeProductFromCart(mouse, 1); // Remove 1 mouse, keep 1
        alice.displayCart();

        // 5. Place an order
        System.out.println("\n5. PLACING ORDER:");
        Order order = placeOrder(alice, "123 Main St, Anytown, USA");

        if (order != null) {
            // 6. Display order summary
            System.out.println("\n6. ORDER SUMMARY:");
            System.out.println(order.generateOrderSummary());

            // 7. Process the order
            System.out.println("7. PROCESSING ORDER:");
            if (order.processOrder()) {
                System.out.println("Order processed successfully!");

                // 8. Update order status
                System.out.println("\n8. ORDER STATUS UPDATES:");
                order.updateOrderStatus(OrderStatus.PROCESSING);
                order.updateOrderStatus(OrderStatus.SHIPPED);
                order.updateOrderStatus(OrderStatus.DELIVERED);
            }

            // 9. Display updated product inventory
            System.out.println("\n9. UPDATED INVENTORY:");
            displayProductCatalog();

            // 10. Display customer order history
            System.out.println("10. CUSTOMER ORDER HISTORY:");
            alice.displayOrderHistory();
        }

        // 11. Create another customer and demonstrate more functionality
        System.out.println("\n11. SECOND CUSTOMER DEMONSTRATION:");
        Customer bob = customers.get(1);
        bob.addProductToCart(productCatalog.get(3), 1); // Monitor
        bob.addProductToCart(productCatalog.get(4), 1); // Headphones
        bob.displayCart();

        Order bobOrder = placeOrder(bob, "456 Oak Ave, Springfield, USA");
        if (bobOrder != null) {
            System.out.println(bobOrder.generateOrderSummary());
            bobOrder.processOrder();
        }

        System.out.println("\n=== DEMONSTRATION COMPLETE ===");
    }

    private static void displayProductCatalog() {
        System.out.println("Available Products:");
        System.out.println("-------------------");
        for (Product product : productCatalog) {
            System.out.printf("%-15s | %-20s | $%-8.2f | Stock: %d | %s%n",
                    product.getProductID(),
                    product.getName(),
                    product.getPrice(),
                    product.getStockQuantity(),
                    product.isInStock() ? "Available" : "Out of Stock");
        }
    }

    private static Order placeOrder(Customer customer, String shippingAddress) {
        if (customer.getShoppingCart().isEmpty()) {
            System.out.println("Cannot place order: Shopping cart is empty.");
            return null;
        }

        String orderID = "ORD" + String.format("%03d", allOrders.size() + 1);
        Order order = new Order(orderID, customer, customer.getShoppingCart(), shippingAddress);

        // Add order to customer's history
        customer.addOrderToHistory(order);

        // Add to global orders list
        allOrders.add(order);

        // Clear customer's cart
        customer.clearCart();

        System.out.println("Order " + orderID + " created successfully!");
        return order;
    }

    // Optional interactive menu method
    private static void runInteractiveMenu() {
        System.out.println("\n=== INTERACTIVE MODE ===");
        System.out.println("This would allow users to interact with the system");
        System.out.println("Features could include:");
        System.out.println("- Browse products");
        System.out.println("- Select customer");
        System.out.println("- Add/remove items from cart");
        System.out.println("- Place orders");
        System.out.println("- View order history");
        System.out.println("- Update order status");
    }
}