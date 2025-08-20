package loco.observer;
import loco.entities.User;


public class UserObserver implements Observer {
    private final User user;
    public UserObserver(User user) { this.user = user; }
    public void update(String message) { System.out.println("User " + user.getName() + " notification: " + message); }
}
