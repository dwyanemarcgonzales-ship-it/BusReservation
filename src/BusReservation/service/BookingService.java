package BusReservation.service;

import BusReservation.Main;
import java.util.ArrayList;
import BusReservation.model.Booking;
import BusReservation.model.Bus;

public class BookingService {

    ArrayList<Booking> bookings = new ArrayList<>();
    int bookingCounter = 1;

    // create booking — returns bookingId or -1 on failure
    public int book(String username, int busId) {
        Booking b = new Booking(bookingCounter++, username, busId);
        bookings.add(b);
        return b.bookingId;
    }

    // Display bookings for a user (both pending and confirmed)
    public void displayUser(String username) {
    boolean found = false;

    for (Booking b : bookings) {

        if (b.username.equals(username)) {

            Bus bus = Main.busService.getById(b.busId);

            System.out.println("\n-----------------------------------");
            System.out.println("Booking ID : " + b.bookingId);
            System.out.println("Status     : " + (b.confirmed ? "CONFIRMED" : "PENDING"));

            if (bus != null) {
                System.out.println("Route      : " + bus.from + " --> " + bus.to);
                System.out.println("Date       : " + bus.date);
                System.out.println("Time       : " + bus.time);
            } else {
                System.out.println("Bus details not found (Bus deleted)");
            }

            System.out.println("-----------------------------------");

            found = true;
        }
    }

    if (!found) {
        System.out.println("No bookings found!");
    }
}

    // Display all bookings (admin)
    public void displayAll() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings yet.");
            return;
        }
        for (Booking b : bookings) {
            System.out.println(b);
        }
    }

    // Display pending bookings only
    public void displayPending() {
        boolean found = false;
        for (Booking b : bookings) {
            if (!b.confirmed) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) System.out.println("No pending bookings.");
    }

    // Confirm booking by bookingId
    public boolean confirmBookingById(int bookingId) {
        for (Booking b : bookings) {
            if (b.bookingId == bookingId && !b.confirmed) {
                b.confirmed = true;
                return true;
            }
        }
        return false;
    }

    // Reject booking by bookingId -> remove only if pending
    public boolean rejectBookingById(int bookingId) {
        return bookings.removeIf(b -> b.bookingId == bookingId && !b.confirmed);
    }

    // helper to check if any booking exists for a bus (not necessary but kept)
    public boolean hasBookingForBus(int busId) {
        for (Booking b : bookings) {
            if (b.busId == busId) return true;
        }
        return false;
    }
}
