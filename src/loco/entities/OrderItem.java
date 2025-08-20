package loco.entities;

public class OrderItem {
    private final Product product;
    private final int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getItemTotal() { return product.getPrice() * quantity; }
    public int getQuantity() { return quantity; }
    public Product getProduct() { return product; }
}

