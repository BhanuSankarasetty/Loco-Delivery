package loco.entities;
import java.util.ArrayList;
import java.util.List;
import loco.observer.OrderNotifier;

public class Product {
    // private final String productId;
    private final String name;
    private final double price;
    private int quantity;
    private final Vendor vendor;
    private final List<Review> reviews = new ArrayList<>();
    private final int LOW_STOCK_THRESHOLD = 3;

    public Product(String productId, String name, double price, int quantity, Vendor vendor) {
        // this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.vendor = vendor;
    }

    // synchronized to prevent race conditions
    public synchronized void reduceQuantity(int amount, OrderNotifier notifier) {
        quantity -= amount;
        if(quantity <= LOW_STOCK_THRESHOLD) {
            // Notify only the vendor
            notifier.notifyVendor(vendor, "Low stock for product " + name + " (Remaining: " + quantity + ")");
        }
    }

    public void addReview(Review review) { reviews.add(review); }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public String getName() { return name; }
    public Vendor getVendor() { return vendor; }

    public double getAverageRating() {
        if (reviews.isEmpty()) return 0;
        return reviews.stream().mapToInt(r -> r.rating).average().orElse(0);
    }

    @Override
public String toString() {
    return "Product{" +
           "name='" + name + '\'' +
           ", price=" + price +
           ", quantity=" + quantity +
           ", vendor=" + vendor.getName() +
           '}';
}

}
