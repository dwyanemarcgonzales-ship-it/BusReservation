package BusReservation.service;

import BusReservation.model.Admin;

public class AdminService {

    Admin admin = new Admin();

    public boolean login(String u, String p) {
        return admin.username.equals(u) && admin.password.equals(p);
    }
}
