package BusReservation.model;

public class Bus {
    public int id;
    public String from;
    public String to;
    public String date;
    public String time;

    public Bus(int id, String f, String t, String d, String ti) {
        this.id = id;
        this.from = f;
        this.to = t;
        this.date = d;
        this.time = ti;
    }

    public int getBusId() { return id; }
    public String getFrom() { return from; }
    public String getTo() { return to; }
    public String getDate() { return date; }
    public String getTime() { return time; }

    @Override
    public String toString() {
        return id + " | " + from + " -> " + to + " | " + date + " | " + time;
    }
}