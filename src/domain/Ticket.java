package domain;

public class Ticket {
    private String raceName;
    private Seat seat;

    public Ticket(String raceName, Seat seat) {
        this.raceName = raceName;
        this.seat = seat;
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
