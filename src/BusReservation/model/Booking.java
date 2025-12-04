package BusReservation.model;

public class Booking {
    public int bookingId;
    public String username;
    public int busId;
    public boolean confirmed; // becomes true when admin approves

    public Booking(int bookingId, String username, int busId) {
        this.bookingId = bookingId;
        this.username = username;
        this.busId = busId;
        this.confirmed = false;
    }

    @Override
    public String toString() {
        return "BookingID: " + bookingId + " | User: " + username + " | BusID: " + busId +
                " | " + (confirmed ? "Confirmed" : "Pending");
    }
}
