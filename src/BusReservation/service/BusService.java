package BusReservation.service;

import java.util.ArrayList;
import BusReservation.model.Bus;

public class BusService {

    ArrayList<Bus> buses = new ArrayList<>();
    int counter = 1;

    public BusService() {
        loadSampleBuses(); // initial sample data
    }

    private void loadSampleBuses() {
        buses.add(new Bus(counter++, "Cabalian", "Maasin", "2025-12-04", "6:00 AM"));
        buses.add(new Bus(counter++, "Maasin", "Cabalian", "2025-12-04", "8:00 AM"));
        buses.add(new Bus(counter++, "Cabalian", "Sogod", "2025-12-04", "7:00 AM"));
        buses.add(new Bus(counter++, "Sogod", "Cabalian", "2025-12-04", "9:00 AM"));
        buses.add(new Bus(counter++, "Cabalian", "Tacloban", "2025-12-04", "10:00 AM"));
        buses.add(new Bus(counter++, "Tacloban", "Cabalian", "2025-12-04", "1:00 PM"));
    }

    public void addBus(String f, String t, String d, String time) {
        Bus added = new Bus(counter++, f, t, d, time);
        buses.add(added);
        System.out.println("\nAdded Bus: " + added); // show details and success
        System.out.println("Bus successfully added!");
    }

    public void display() {
        if (buses.isEmpty()) {
            System.out.println("No buses available.");
            return;
        }
        for (Bus b : buses) {
            System.out.println(b);
        }
    }

    public void display(ArrayList<Bus> list) {
        if (list.isEmpty()) {
            System.out.println("No buses to display.");
            return;
        }
        for (Bus b : list) {
            System.out.println(b);
        }
    }

    public ArrayList<Bus> search(String f, String t) {
        ArrayList<Bus> result = new ArrayList<>();
        for (Bus b : buses) {
            if (b.from.equalsIgnoreCase(f) && b.to.equalsIgnoreCase(t)) {
                result.add(b);
            }
        }
        return result;
    }

    public void removeBus(int id) {
        boolean removed = buses.removeIf(b -> b.id == id);
        if (removed) System.out.println("Deleted bus with ID " + id);
        else System.out.println("Bus ID not found.");
    }

    public boolean exists(int id) {
        for (Bus b : buses) {
            if (b.id == id) return true;
        }
        return false;
    }

    public Bus getById(int id) {
        for (Bus b : buses) {
            if (b.id == id) return b;
        }
        return null;
    }
}

