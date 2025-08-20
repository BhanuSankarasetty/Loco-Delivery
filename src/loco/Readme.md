# Loco - A Simplified Local Delivery System

Loco is a Java-based simulation of a simplified local delivery system. It demonstrates object-oriented design, design patterns (Observer & Strategy), and multi-threading for order status updates.

---

## **Table of Contents**
- [Features](#features)
- [Architecture & Design](#architecture--design)
- [Technologies](#technologies)
- [Setup & Run](#setup--run)
- [Folder Structure](#folder-structure)
- [Demo Output](#demo-output)
- [License](#license)

---

## **Features**
- **User & Vendor Management**  
  Users can place orders, write reviews; vendors can manage products and receive notifications.
- **Order Management**  
  Orders progress automatically through statuses: `PLACED → PROCESSING → DISPATCHED → DELIVERED`.
- **Observer Pattern**  
  Notifies users and vendors of order updates and low stock products.
- **Strategy Pattern**  
  Supports multiple payment methods like Credit Card and UPI.
- **Stock Management**  
  Prevents ordering beyond available stock and alerts vendors of low inventory.
- **Review System**  
  Users can review products and vendors; average ratings are calculated.

---

## **Architecture & Design**
- **Entities:** `User`, `Vendor`, `Product`, `Order`, `Review`  
- **Services:** `AdminService`, `OrderService`, `ReviewService`  
- **Design Patterns:**  
  - **Observer Pattern:** Real-time notifications to users & vendors.  
  - **Strategy Pattern:** Flexible payment processing.  
- **Concurrency:** Order status progression handled with Java threads.  

---

## **Technologies**
- **Java SE 23**
- **Collections & Streams**
- **Multithreading**
- **Object-Oriented Design**

---

## **Setup & Run**
1. **Clone the repository**

```
git clone https://github.com/<your-username>/Loco.git
cd Loco/LocalMart/src
```


- **Compile all classes**

```
javac loco/*.java
```

- **Run the application**

```
java loco.Loco
```
Note: Ensure your package declaration matches the folder structure (package loco; in all .java files).

## Folder Structure

```
LocalMart/
├─ src/
│  ├─ loco/
│  │  ├─ Main.java
│  │  ├─ entities/
│  │  │  ├─ User.java
│  │  │  ├─ Vendor.java
│  │  │  ├─ Product.java
│  │  │  └─ Review.java
│  │  ├─ observer/
│  │  │  └─ Observer.java
│  │  ├─ payment/
│  │  │  ├─ PaymentStrategy.java
│  │  │  ├─ CreditCardPayment.java
│  │  │  └─ UpiPayment.java
│  │  └─ services/
│  │     ├─ AdminService.java
│  │     ├─ OrderService.java
│  │     └─ ReviewService.java

```

## Demo Output

```
Vendor Tasty Foods approved!
Vendor Quick Bites approved!
Vendor Tasty Foods notification: Low stock for product Paneer Curry (Remaining: 2)
User Bhanu notification: New order placed: <UUID>
Vendor Tasty Foods notification: New order received: <UUID>
Paid 550.0 via Credit Card.
Not enough stock for product: Paneer Curry
Order not found: some-fake-id
Review added by Bhanu for Paneer Curry
Review added by Bhanu for Veg Biryani
Product p1 average rating: 5.0
Product p2 average rating: 4.0
Vendor1 average rating: 4.5
...

```