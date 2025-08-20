package loco.services;
import loco.entities.Vendor;
import loco.observer.OrderNotifier;


public class AdminService {
    private final OrderNotifier notifier = new OrderNotifier();

    public void approveVendor(Vendor vendor) {
        vendor.approve();
        System.out.println("Vendor " + vendor.getName() + " approved!");
        notifier.addVendorObserver(vendor);
        notifier.notifyVendor(vendor, "Your vendor account has been approved.");
    }

    public OrderNotifier getNotifier() { return notifier; }
}

