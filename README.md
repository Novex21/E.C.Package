# E-Commerce System (Java)

## 📌 Overview
This project is a **simple e-commerce system** built in Java. It allows customers to:
- Browse available products
- Add products to a shopping cart
- Place orders
- View product, customer, and order details

The project demonstrates the use of **Java packages** and the `import` statement for proper organization and encapsulation.

---
## 📂 Project Structure


- **com.ecommerce**
    - `Product` → Represents a product with attributes like `productID`, `name`, and `price`.
    - `Customer` → Represents a customer with `customerID`, `name`, and a **shopping cart**. Provides methods for managing the cart and placing orders.

- **com.ecommerce.orders**
    - `Order` → Represents an order placed by a customer. Includes `orderID`, customer details, products, order total, and status.

- **Main.java**
    - Demonstrates creating products, customers, and orders.
    - Allows customers to add products to their cart and place orders.
    - Displays order summaries.

---

## ⚙️ Features

- **Product Management**  
  Create and display product details.

- **Shopping Cart**  
  Add and remove products, calculate the total price.

- **Order Processing**  
  Place an order, generate order summary, and update order status.

- **Encapsulation & Packages**  
  Proper use of Java packages (`com.ecommerce`, `com.ecommerce.orders`) with `import` statements.

---

## ▶️ How to Run

1. **Clone or download** this repository.
2. Open the project in your preferred Java IDE (e.g., IntelliJ, Eclipse, VS Code).
3. Ensure the package structure is preserved (`com/ecommerce` and `com/ecommerce/orders`).
4. Compile the project:
   ```bash
   javac src/com/ecommerce/*.java src/com/ecommerce/orders/*.java src/Main.java
   java -cp src Main
   
   
