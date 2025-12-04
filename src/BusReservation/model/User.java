package BusReservation.model;

public class User {
    public String username;
    public String password;
    public String fullname;
    public boolean approved; // admin must approve registration

    public User(String u, String p, String f) {
        username = u;
        password = p;
        fullname = f;
        approved = false; // pending by default
    }

    @Override
    public String toString() {
        return fullname + " (" + username + ") - " + (approved ? "Approved" : "Pending");
    }
}