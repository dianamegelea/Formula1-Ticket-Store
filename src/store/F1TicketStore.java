package store;

import domain.*;
import exceptions.ClientExistsException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class F1TicketStore {
    private List<F1Race> races;
    private List<Customer> customers;

    private static F1TicketStore instance;
    private final Object purchaseLock = new Object();
    private final ExecutorService threadPool = Executors.newFixedThreadPool(10);

    private F1TicketStore() {
        races = new ArrayList<>();
        customers = Collections.synchronizedList(new ArrayList<>());
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

    public void setRaces(List<F1Race> races) {
        this.races = races;
    }

    public void addCustomerAsync(Customer customer) {
        threadPool.submit(() -> {
            try {
                registerCustomer(customer);
            } catch (ClientExistsException e) {
                e.printStackTrace();
            }
        });
    }

    private void registerCustomer(Customer newCustomer) throws ClientExistsException {
        if (customers.contains(newCustomer)) {
            throw new ClientExistsException(newCustomer.getName());
        }
        customers.add(newCustomer);
    }

    public void addRaceToCalendar(F1Race race) {
        races.add(race);
    }

    public void addAvailableSeatsToRace(F1Race race, List<Seat> seats) {
        Optional<F1Race> r = races.stream().filter(f1race -> f1race.equals(race)).findFirst();
        r.ifPresent(f1Race -> f1Race.getSeats().addAll(seats));
    }

    public boolean purchaseTicketAsync(Customer customer, F1Race race, Seat seat) {
        threadPool.submit(() -> purchaseTicket(customer, race, seat));
        return true; // Assuming success (confirmation will be handled asynchronously)
    }

    public boolean purchaseTicket(Customer customer, F1Race race, Seat seat) {
        if (race.getSeats().contains(seat) && seat.isAvailable()) {
            synchronized (purchaseLock) {
                Ticket ticket = new Ticket(race.getRaceName(), seat);
                customer.getPurchasedTickets().add(ticket);
                seat.setAvailable(false);
                race.incrementSoldTickets();
                return true;
            }
        } else {
            return false;
        }
    }
}