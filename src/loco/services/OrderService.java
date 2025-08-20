package loco.services;
import loco.entities.Order;
import loco.entities.OrderItem;
import loco.entities.OrderStatus;
import loco.entities.User;
import loco.entities.Vendor;
import loco.observer.OrderNotifier;
import loco.payment.PaymentStrategy;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.List;


public class OrderService {
    private final Map<String, Order> orders = new HashMap<>();
    private final OrderNotifier notifier;

    public OrderService(OrderNotifier notifier) {
        this.notifier = notifier;
    }

    public Order placeOrder(User user, Vendor vendor, List<OrderItem> items, PaymentStrategy payment) {
        if (!vendor.isApproved()) {
            System.out.println("Vendor not approved: " + vendor.getName());
            return null;
        }

        // Check stock availability
        for (OrderItem item : items) {
            if (item.getQuantity() > item.getProduct().getQuantity()) {
                System.out.println("Not enough stock for product: " + item.getProduct().getName());
                return null;
            }
        }

        // Reduce product quantity & notify vendor if low stock
        for (OrderItem item : items) {
            item.getProduct().reduceQuantity(item.getQuantity(), notifier);
        }

        // Create order
        Order order = new Order(UUID.randomUUID().toString(), user, vendor, items);
        orders.put(order.getOrderId(), order);

        // Add order to user & vendor history
        user.addOrder(order);
        vendor.addOrder(order);

        // Register observers
        notifier.addUserObserver(user);
        notifier.addVendorObserver(vendor);

        // Notify both parties
        notifier.notifyUser(user, "New order placed: " + order.getOrderId());
        notifier.notifyVendor(vendor, "New order received: " + order.getOrderId());

        // Process payment
        payment.pay(order.getTotalPrice());

        // Simulate order status progression
        simulateOrderProgress(order);

        return order;
    }

    public void updateOrderStatus(String orderId, OrderStatus status) {
        Order order = orders.get(orderId);
        if (order != null) {
            order.updateStatus(status);
            notifier.notifyUser(order.getUser(), "Order " + orderId + " is now " + status);
            notifier.notifyVendor(order.getVendor(), "Order " + orderId + " is now " + status);
        } else {
            System.out.println("Order not found: " + orderId);
        }
    }

    public Order getOrder(String orderId) { return orders.get(orderId); }

    // Automatically progress order through statuses
    public void simulateOrderProgress(Order order) {
        new Thread(() -> {
            try {
                Thread.sleep(2000); // 2s to PROCESSING
                updateOrderStatus(order.getOrderId(), OrderStatus.PROCESSING);

                Thread.sleep(2000); // 2s to DISPATCHED
                updateOrderStatus(order.getOrderId(), OrderStatus.DISPATCHED);

                Thread.sleep(2000); // 2s to DELIVERED
                updateOrderStatus(order.getOrderId(), OrderStatus.DELIVERED);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}


