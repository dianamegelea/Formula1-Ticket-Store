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
        if (race.getSeats().contains(seat) && seat.isAvailable()) {
            synchronized (this) {
                Ticket ticket = new Ticket(race.getRaceName(), seat);
                customer.getPurchasedTickets().add(ticket);
                seat.setAvailable(false);
                race.incrementSoldTickets();
            }
        }
    }
}
