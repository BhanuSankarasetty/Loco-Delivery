package loco.entities;
import java.util.ArrayList;
import java.util.List;

public class Vendor {
    // private final String vendorId;
    private final String name;
    private boolean approved = false;
    private final List<Product> products = new ArrayList<>();
    private final List<Review> reviews = new ArrayList<>();
    private final List<Order> orderHistory = new ArrayList<>();

    public void addOrder(Order order) { orderHistory.add(order); }
    public List<Order> getOrderHistory() { return new ArrayList<>(orderHistory); }

    public Vendor(String vendorId, String name) { 
        // this.vendorId = vendorId;
        this.name = name;
    }

    public void addProduct(Product product) { products.add(product); }
    public void removeProduct(Product product) { products.remove(product); }
    public void approve() { approved = true; }
    public boolean isApproved() { return approved; }
    public void addReview(Review review) { reviews.add(review); }
    public String getName() { return name; }
    public List<Product> getProducts() { return new ArrayList<>(products); }

    public double getAverageRating() {
        if (reviews.isEmpty()) return 0;
        return reviews.stream().mapToInt(r -> r.rating).average().orElse(0);
    }

    @Override
    public String toString() {
        return "Vendor{" + "name='" + name + '\'' + '}';
    }
}

