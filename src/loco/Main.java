package loco;

import loco.entities.*;
import loco.services.*;
import loco.payment.*;
import loco.observer.*;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        AdminService adminService = new AdminService();
        OrderNotifier notifier = adminService.getNotifier();
        OrderService orderService = new OrderService(notifier);
        ReviewService reviewService = new ReviewService();

        User user1 = new User("U001", "Bhanu", "bhanu@gmail.com", "Guntur");
        User user2 = new User("U002", "Ravi", "ravi@gmail.com", "Hyderabad");

        Vendor vendor1 = new Vendor("V001", "Tasty Foods");
        Vendor vendor2 = new Vendor("V002", "Quick Bites");

        adminService.approveVendor(vendor1);
        adminService.approveVendor(vendor2);

        Product p1 = new Product("P001", "Paneer Curry", 200, 4, vendor1); 
        Product p2 = new Product("P002", "Veg Biryani", 150, 5, vendor1);
        Product p3 = new Product("P003", "Chicken Fry", 250, 8, vendor2);

        vendor1.addProduct(p1); vendor1.addProduct(p2);
        vendor2.addProduct(p3);

        List<OrderItem> itemsUser1 = Arrays.asList(
                new OrderItem(p1, 2),
                new OrderItem(p2, 1)
        );

        List<OrderItem> itemsUser2 = Arrays.asList(
                new OrderItem(p3, 5),
                new OrderItem(p1, 3) // should trigger not enough stock
        );

        orderService.placeOrder(user1, vendor1, itemsUser1, new CreditCardPayment());
        orderService.placeOrder(user2, vendor2, itemsUser2, new UpiPayment());

        orderService.updateOrderStatus("some-fake-id", OrderStatus.DISPATCHED); // demo not found case

        reviewService.addReview(user1, p1, vendor1, 5, "Delicious Paneer Curry!");
        reviewService.addReview(user1, p2, vendor1, 4, "Biryani was good.");

        System.out.println("Product p1 average rating: " + p1.getAverageRating());
        System.out.println("Product p2 average rating: " + p2.getAverageRating());
        System.out.println("Vendor1 average rating: " + vendor1.getAverageRating());

        // Print order history for users
        System.out.println("\n--- User Order Histories ---");
        for (User user : Arrays.asList(user1, user2)) {
            System.out.println(user.getName() + "'s Orders:");
            for (Order o : user.getOrderHistory()) {
                System.out.println(o);
            }
        }

        // Print order history for vendors
        System.out.println("\n--- Vendor Order Histories ---");
        for (Vendor vendor : Arrays.asList(vendor1, vendor2)) {
            System.out.println(vendor.getName() + "'s Orders:");
            for (Order o : vendor.getOrderHistory()) {
                System.out.println(o);
            }
        }

    }
}