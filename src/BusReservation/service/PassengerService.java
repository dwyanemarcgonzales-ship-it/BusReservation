package BusReservation.service;

import java.util.ArrayList;
import BusReservation.model.User;

public class PassengerService {

    ArrayList<User> users = new ArrayList<>();

    public void register(String u, String p, String f) {
        // check username uniqueness
        for (User user : users) {
            if (user.username.equalsIgnoreCase(u)) {
                System.out.println("Username already exists. Choose another username.");
                return;
            }
        }
        User newUser = new User(u, p, f);
        users.add(newUser);
        System.out.println("Registered successfully! Waiting for admin approval.");
    }

    // returns User if login ok and approved
    public User login(String u, String p) {
        for (User user : users) {
            if (user.username.equals(u) && user.password.equals(p)) {
                if (!user.approved) {
                    System.out.println("Your account is still pending admin approval.");
                    return null;
                }
                return user;
            }
        }
        return null;
    }

    // admin area
    public ArrayList<User> getAllUsers() {
        return users;
    }

    public ArrayList<User> getPendingUsers() {
        ArrayList<User> pending = new ArrayList<>();
        for (User u : users) if (!u.approved) pending.add(u);
        return pending;
    }

    public boolean approveUser(String username) {
        for (User u : users) {
            if (u.username.equals(username)) {
                u.approved = true;
                return true;
            }
        }
        return false;
    }

    public boolean rejectUser(String username) {
        return users.removeIf(u -> u.username.equals(username) && !u.approved);
    }
}