package loco.entities;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



public class Order {

    private final String orderId;
    private final User user;
    private final Vendor vendor;
    private final List<OrderItem> items;
    private final double totalPrice;
    private OrderStatus status;

    public Order(String orderId, User user, Vendor vendor, List<OrderItem> items) {
        this.orderId = orderId;
        this.user = user;
        this.vendor = vendor;
        this.items = new ArrayList<>(items);
        this.totalPrice = items.stream().mapToDouble(OrderItem::getItemTotal).sum();
        this.status = OrderStatus.PLACED;
    }

    public void updateStatus(OrderStatus status) { this.status = status; }
    public double getTotalPrice() { return totalPrice; }
    public String getOrderId() { return orderId; }
    public User getUser() { return user; }
    public Vendor getVendor() { return vendor; }
    public List<OrderItem> getItems() { return new ArrayList<>(items); }
    public OrderStatus getStatus() { return status; }

    @Override
    public String toString() {
        return "Order{" +
               "orderId='" + orderId + '\'' +
               ", items=" + items.stream().map(i -> i.getProduct().getName() + " x" + i.getQuantity()).collect(Collectors.joining(", ")) +
               ", totalPrice=" + totalPrice +
               ", status=" + status +
               '}';
    }
    
}
