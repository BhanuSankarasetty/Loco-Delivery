package loco.entities;
import java.util.ArrayList;
import java.util.List;

public class User {
    private final String userId;
    private final String name;
    private final String email;
    private final String address;
    private final List<Review> reviews = new ArrayList<>();
    private final List<Order> orderHistory = new ArrayList<>();

    public User(String userId, String name, String email, String address) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public void addReview(Review review) { reviews.add(review); }
    public void addOrder(Order order) { orderHistory.add(order); }
    public List<Order> getOrderHistory() { return new ArrayList<>(orderHistory); }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + '}';
    }
}
