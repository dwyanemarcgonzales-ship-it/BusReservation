package BusReservation;

import BusReservation.service.*;

public class Main {
    public static BusService busService = new BusService();
    public static BookingService bookingService = new BookingService();
    public static PassengerService passengerService = new PassengerService();
    public static AdminService adminService = new AdminService();
    
    public static void main(String[] args) {
        LoginMenu.start();
    }
}
