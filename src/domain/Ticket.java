package domain;

import utils.Pair;

import java.util.concurrent.atomic.AtomicInteger;

public class Ticket {
    private AtomicInteger ticketsCounter = new AtomicInteger(0);
    private int ticketId;
    private String raceName;
    private Category category;
    private String grandstand;
    private Pair<Integer, Integer> seat;
    private double price;

    public Ticket(String raceName, Category category, double price) {
        this.ticketId = ticketsCounter.getAndIncrement();
        this.raceName = raceName;
        this.category = category;
        this.grandstand = null;
        this.seat = null;
        this.price = price;
    }

    public Ticket(String raceName, Category category, String grandstand, int row, int seatNumber, double price) {
        this.ticketId = ticketsCounter.getAndIncrement();
        this.raceName = raceName;
        this.category = category;
        this.grandstand = grandstand;
        this.seat = new Pair<>(row, seatNumber);
        this.price = price;
    }

    public int getTicketId() {
        return ticketId;
    }

    public String getRaceName() {
        return raceName;
    }

    public Category getCategory() {
        return category;
    }

    public Pair<Integer, Integer> getSeat() {
        return seat;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "raceName='" + raceName + '\'' +
                ", category=" + category +
                ", grandstand='" + grandstand + '\'' +
                ", seat=" + seat +
                ", price=" + price +
                '}';
    }
}
