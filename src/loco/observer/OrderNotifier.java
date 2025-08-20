package loco.observer;
import java.util.HashMap;
import java.util.Map;
import loco.entities.User;
import loco.entities.Vendor;

/**
 * OrderNotifier class that manages observers for users and vendors.
 * It allows adding observers and notifying them with messages.
 */

public class OrderNotifier {
    private final Map<User, UserObserver> userObservers = new HashMap<>();
    private final Map<Vendor, VendorObserver> vendorObservers = new HashMap<>();

    public void addUserObserver(User user) {
        userObservers.putIfAbsent(user, new UserObserver(user));
    }

    public void addVendorObserver(Vendor vendor) {
        vendorObservers.putIfAbsent(vendor, new VendorObserver(vendor));
    }

    public void notifyUser(User user, String message) {
        UserObserver observer = userObservers.get(user);
        if(observer != null) observer.update(message);
    }

    public void notifyVendor(Vendor vendor, String message) {
        VendorObserver observer = vendorObservers.get(vendor);
        if(observer != null) observer.update(message);
    }
}

