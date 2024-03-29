package domain;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class F1Race {
    private int raceNumber;
    private String raceName;
    private String location;
    private Date date;
    private int maximumAttendance;
    private AtomicInteger soldTickets;
    private List<Seat> seats;

    public F1Race(int raceNumber, String raceName, String location, Date date,
                  int maximumAttendance) {
        this.raceNumber = raceNumber;
        this.raceName = raceName;
        this.location = location;
        this.date = date;
        this.maximumAttendance = maximumAttendance;
        this.seats = new ArrayList<>();
        this.soldTickets = new AtomicInteger(0);
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public String getRaceName() {
        return raceName;
    }

    public int getSoldTickets() {
        return soldTickets.get();
    }

    public void incrementSoldTickets() {
        soldTickets.incrementAndGet();
    }

    @Override
    public String toString() {
        return "F1Race{" +
                "raceName='" + raceName + '\'' +
                ", location='" + location + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        F1Race f1Race = (F1Race) o;
        return Objects.equals(raceName, f1Race.raceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(raceName);
    }
}
