package loco.services;
import loco.entities.Product;
import loco.entities.Review;
import loco.entities.User;
import loco.entities.Vendor;
import java.util.UUID;


public class ReviewService {
    public void addReview(User user, Product product, Vendor vendor, int rating, String comment) {
        Review review = new Review(UUID.randomUUID().toString(), user, product, vendor, rating, comment);
        product.addReview(review);
        vendor.addReview(review);
        user.addReview(review);
        System.out.println("Review added by " + user.getName() + " for " + product.getName());
    }
}
