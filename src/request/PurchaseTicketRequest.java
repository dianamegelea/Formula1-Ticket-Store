package request;

import domain.Customer;
import domain.F1Race;
import domain.Seat;
import domain.Ticket;

public class PurchaseTicketRequest implements Runnable {
    private F1Race race;
    private Seat seat;
    private Customer customer;

    public PurchaseTicketRequest(Customer customer, F1Race race, Seat seat) {
        this.race = race;
        this.seat = seat;
        this.customer = customer;
    }

    @Override
    public void run() {
        if (race.getSeats().contains(seat)) {
            Ticket ticket = new Ticket(race.getRaceName(), seat);
            synchronized (this) {
                if (seat.isAvailable()) {
                    seat.setAvailable(false);
                    customer.getPurchasedTickets().add(ticket);
                }
            }
            race.incrementSoldTickets();
        }
    }
}
