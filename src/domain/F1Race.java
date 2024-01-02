package domain;

import java.time.LocalDateTime;
import java.util.*;

public class F1Race {
    private int raceNumber;
    private String raceName;
    private String location;
    private Date date;
    private int maximumAttendance;
    private int soldTickets;
    private List<Ticket> availableTickets;

    public F1Race(int raceNumber, String raceName, String location, Date date, int maximumAttendance) {
        this.raceNumber = raceNumber;
        this.raceName = raceName;
        this.location = location;
        this.date = date;
        this.maximumAttendance = maximumAttendance;
        this.availableTickets = new ArrayList<>();
        this.soldTickets = 0;
    }

    public String getRaceName() {
        return raceName;
    }

    public List<Ticket> getAvailableTickets() {
        return availableTickets;
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
}
