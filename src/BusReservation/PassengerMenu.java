package BusReservation;

import java.util.Scanner;
import BusReservation.model.*;
import BusReservation.service.*;

public class PassengerMenu {

    static Scanner sc = new Scanner(System.in);
    static BusService busService = Main.busService;
    static BookingService bookingService = Main.bookingService;

    public static void start(User user) {

        while (true) {
            System.out.println("\n--- PASSENGER MENU ---");
            System.out.println("1. Search Bus");
            System.out.println("2. My Bookings");
            System.out.println("3. Logout");
            System.out.print("Choice: ");

            String line = sc.nextLine();
            int c;
            try {
                c = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter number.");
                continue;
            }

            switch (c) {
                case 1 -> searchBus(user);
                case 2 -> bookingService.displayUser(user.username);
                default -> { return; }
            }
        }
    }

    static void searchBus(User user) {
        boolean continueBooking = true;

        while (continueBooking) {
            System.out.println("\n=== Available Buses ===");
            busService.display(); // display all buses without filtering

            System.out.print("\nPlease enter Bus ID to book (or 0 to cancel): ");
            String line = sc.nextLine();
            int id;
            try {
                id = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid Bus ID.");
                continue;
            }
            if (id == 0) return;

            if (!busService.exists(id)) {
                System.out.println("Invalid Bus ID!");
                continue;
            }

            // show details
            Bus selected = busService.getById(id);
            System.out.println("\n=== Bus Details ===");
            System.out.println("Bus ID: " + selected.id);
            System.out.println("From: " + selected.from);
            System.out.println("To: " + selected.to);
            System.out.println("Date: " + selected.date);
            System.out.println("Time: " + selected.time);

            System.out.print("\nConfirm booking? (Y/N): ");
            String confirm = sc.nextLine().trim().toLowerCase();
            if (confirm.equals("y")) {
                int bookingId = bookingService.book(user.username, id);
                System.out.println("Booking requested! Booking ID: " + bookingId + " (Pending admin approval).");
            } else {
                System.out.println("Booking canceled.");
            }

            // ask if add more
            System.out.print("\nDo you want to add more booking? (Y/N): ");
            String addMore = sc.nextLine().trim().toLowerCase();
            if (!addMore.equals("y")) continueBooking = false;
        }
    }
}