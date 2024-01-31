package store;

import domain.*;
import exceptions.ClientExistsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class F1TicketStore {
    private List<F1Race> races;
    private List<Customer> customers;
    private F1MerchStore merchStore;

    private static F1TicketStore instance;
    private final Object purchaseLock = new Object();
    public static final ExecutorService threadPool =
            Executors.newFixedThreadPool(8);

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

    public void setRaces(List<F1Race> races) {
        this.races = races;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public F1MerchStore getMerchStore() {
        return merchStore;
    }

    public void setMerchStore(F1MerchStore merchStore) {
        this.merchStore = merchStore;
    }

    public void addCustomer(Customer customer) {
        Future<?> future = threadPool.submit(() -> {
            try {
                registerCustomer(customer);
            } catch (ClientExistsException e) {
                e.printStackTrace();
            }
        });

        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void registerCustomer(Customer newCustomer)
            throws ClientExistsException {
        if (customers.contains(newCustomer)) {
            throw new ClientExistsException(newCustomer.getName());
        }
        customers.add(newCustomer);
    }

    public void addRaceToCalendar(F1Race race) {
        races.add(race);
    }

    public void addAvailableSeatsToRace(F1Race race, List<Seat> seats) {
        Optional<F1Race> r = races.stream()
                .filter(f1race -> f1race.equals(race)).findFirst();
        r.ifPresent(f1Race -> f1Race.getSeats().addAll(seats));
    }

    public void purchaseTicket(Customer customer, F1Race race, Seat seat) {
        Future<?> future = threadPool.submit(() ->
                buyTicket(customer, race, seat));
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void buyTicket(Customer customer, F1Race race, Seat seat) {
        if (race.getSeats().contains(seat) && seat.isAvailable()) {
            synchronized (purchaseLock) {
                Ticket ticket = new Ticket(race.getRaceName(), seat);
                customer.getPurchasedTickets().add(ticket);
                seat.setAvailable(false);
                race.incrementSoldTickets();
            }
        }
    }
}