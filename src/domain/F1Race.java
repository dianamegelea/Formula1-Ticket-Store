package domain;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class F1Race {
    private int raceNumber;
    private String raceName;
    private String location;
    private Date date;
    private int maximumAttendance;
    private int soldTickets;
    private List<Seat> seats;

    public F1Race(int raceNumber, String raceName, String location, Date date, int maximumAttendance) {
        this.raceNumber = raceNumber;
        this.raceName = raceName;
        this.location = location;
        this.date = date;
        this.maximumAttendance = maximumAttendance;
        this.seats = new CopyOnWriteArrayList<>();
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public String getRaceName() {
        return raceName;
    }

    public int getMaximumAttendance() {
        return maximumAttendance;
    }

    public int getSoldTickets() {
        return soldTickets;
    }

    public void incrementSoldTickets() {
        this.soldTickets++;
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
