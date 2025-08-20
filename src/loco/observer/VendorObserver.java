package loco.observer;
import loco.entities.Vendor;

public class VendorObserver implements Observer {
    private final Vendor vendor;
    public VendorObserver(Vendor vendor) { this.vendor = vendor; }
    public void update(String message) { System.out.println("Vendor " + vendor.getName() + " notification: " + message); }
}

