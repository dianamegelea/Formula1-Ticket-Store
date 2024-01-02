package store;

import domain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class F1TicketStore {
    private List<F1Race> races;
    private List<Customer> customers;

    private static F1TicketStore instance;
    private final Object purchaseLock = new Object();

    private F1TicketStore() {
        races = new ArrayList<>();
        customers = new ArrayList<>();
    }

    public static synchronized F1TicketStore getInstance() {
        if (instance == null) {
            instance = new F1TicketStore();
        }
        return instance;
    }

    public List<F1Race> getRaces() {
        return races;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void addRaceToCalendar(F1Race race) {
        races.add(race);
    }

    public void registerCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addTicketsToRace(F1Race race, Category category, List<Ticket> tickets) {
        if (race.getMaximumAttendance() >= race.getSoldTickets() + race.getAvailableTickets().size() + tickets.size()) {
            race.getAvailableTickets().addAll(tickets);
        } else {
            throw new RuntimeException("Couldn't add tickets. Would exceed the maximum capacity of the track");
        }
    }

    public boolean purchaseTicket(Customer customer, F1Race race, Ticket ticket) {
        synchronized (purchaseLock) {
            // Check if the ticket is available for purchase
            if (race.getAvailableTickets().contains(ticket)) {
                // Make the purchase
                customer.purchaseTicket(ticket);
                race.getAvailableTickets().remove(ticket);
                race.incrementSoldTickets();
                return true; // Successful purchase
            } else {
                return false; // Ticket not available or already purchased
            }
        }
    }
}
