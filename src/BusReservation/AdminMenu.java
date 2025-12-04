package BusReservation;

import java.util.Scanner;
import BusReservation.service.*;
import BusReservation.model.User;

public class AdminMenu {

    static Scanner sc = new Scanner(System.in);
    static BusService busService = Main.busService;
    static BookingService bookingService = Main.bookingService;
    static PassengerService passengerService = Main.passengerService;
    

    public static void start() {

        while (true) {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1. Add Bus");
            System.out.println("2. View/Remove Bus");
            System.out.println("3. View/Confirm Bookings");
            System.out.println("4. Approve/Reject Registrations");
            System.out.println("5. Logout");
            System.out.print("Choice: ");

            String line = sc.nextLine();
            int c;
            try {
                c = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number.");
                continue;
            }

            switch (c) {
                case 1 -> addBus();
                case 2 -> viewRemoveBus();
                case 3 -> confirmBookings();
                case 4 -> handleRegistrations();
                default -> { return; }
            }
        }
    }

    static void addBus() {
        System.out.print("From: ");
        String f = sc.nextLine().trim();
        System.out.print("To: ");
        String t = sc.nextLine().trim();
        System.out.print("Date(mm-dd-yy): ");
        String d = sc.nextLine().trim();
        System.out.print("Time: ");
        String time = sc.nextLine().trim();

        if (f.isEmpty() || t.isEmpty() || d.isEmpty() || time.isEmpty()) {
            System.out.println("All fields are required. Bus not added.");
            return;
        }

        busService.addBus(f, t, d, time);
    }

    static void viewRemoveBus() {
        busService.display();

        System.out.print("Enter Bus ID to delete (0 to cancel): ");
        String line = sc.nextLine();
        int id;
        try {
            id = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Bus ID.");
            return;
        }

        if (id == 0) return;

        // if bus has confirmed bookings, ask confirmation
        if (bookingService.hasBookingForBus(id)) {
            System.out.print("There are bookings for this bus. Are you sure to delete? (Y/N): ");
            char ch = sc.nextLine().trim().toLowerCase().charAt(0);
            if (ch != 'y') {
                System.out.println("Delete cancelled.");
                return;
            }
        }

        busService.removeBus(id);
    }

    static void confirmBookings() {
        System.out.println("\n--- Pending Bookings ---");
        bookingService.displayPending();

        System.out.print("\nEnter Booking ID to approve/reject (0 to cancel): ");
        String line = sc.nextLine();
        int id;
        try {
            id = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Booking ID.");
            return;
        }
        if (id == 0) return;

        System.out.print("Approve (A) or Reject (R)? ");
        String act = sc.nextLine().trim().toLowerCase();
        if (act.equals("a")) {
            boolean ok = bookingService.confirmBookingById(id);
            if (ok) System.out.println("Booking approved!");
            else System.out.println("Booking not found or already approved.");
        } else if (act.equals("r")) {
            boolean ok = bookingService.rejectBookingById(id);
            if (ok) System.out.println("Booking rejected and removed.");
            else System.out.println("Booking not found or already approved.");
        } else {
            System.out.println("Invalid input please enter 'A' for approved 'R' for reject.");
        }
    }

    static void handleRegistrations() {
        System.out.println("\n--- Pending Registrations ---");
        var pending = passengerService.getPendingUsers();
        if (pending.isEmpty()) {
            System.out.println("No pending registrations.");
            return;
        }
        for (User u : pending) {
            System.out.println(u);
        }

        System.out.print("\nEnter username to approve/reject (or 0 to cancel): ");
        String username = sc.nextLine().trim();
        if (username.equals("0")) return;

        System.out.print("Approve (A) or Reject (R)? ");
        String act = sc.nextLine().trim().toLowerCase();
        if (!act.equals("a")) if (act.equals("r")) {
            boolean ok = passengerService.rejectUser(username);
            if (ok) System.out.println("User rejected and removed.");
            else System.out.println("User not found or already approved.");
        } else {
            System.out.println("Invalid input please enter 'A' for approved 'R' for reject.");
        } else {
            boolean ok = passengerService.approveUser(username);
            if (ok) System.out.println("User approved.");
            else System.out.println("User not found or already approved.");
        }
    }
}