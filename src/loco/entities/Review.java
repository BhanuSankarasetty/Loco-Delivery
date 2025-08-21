package loco.entities;

public class Review {
    public final int rating;
    // private final String reviewId;
    // private final User user;
    // private final Product product;
    // private final Vendor vendor;
    private final String comment;

    public Review(String reviewId, User user, Product product, Vendor vendor, int rating, String comment) {
        if(rating < 1 || rating > 5) throw new IllegalArgumentException("Rating must be between 1 and 5");
        // this.reviewId = reviewId;
        // this.user = user;
        // this.product = product;
        // this.vendor = vendor;
        this.rating = rating;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Review{" + "rating=" + rating + ", comment='" + comment + '\'' + '}';
    }
}
