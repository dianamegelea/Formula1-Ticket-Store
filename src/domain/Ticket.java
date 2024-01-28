package domain;

import utils.Pair;

import java.util.concurrent.atomic.AtomicInteger;

public class Ticket {
    private static AtomicInteger ticketsCounter = new AtomicInteger(0);
    private int ticketId;
    private String raceName;
    private Seat seat;

    public Ticket(String raceName, Seat seat) {
        this.ticketId = ticketsCounter.getAndIncrement();
        this.raceName = raceName;
        this.seat = seat;
    }

    public int getTicketId() {
        return ticketId;
    }

    public String getRaceName() {
        return raceName;
    }

    public Seat getSeat() {
        return seat;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "raceName='" + raceName + '\'' +
                ", seat=" + seat +
                '}';
    }
}
