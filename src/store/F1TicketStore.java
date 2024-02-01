package store;

import domain.*;
import exceptions.ClientExistsException;
import request.PurchaseTicketRequest;
import request.RegisterCustomerRequest;

import java.util.ArrayList;
import java.util.Collections;
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
    private ExecutorService threadPool;

    public F1TicketStore() {
        races = Collections.synchronizedList(new ArrayList<>());
        customers = Collections.synchronizedList(new ArrayList<>());
        threadPool = Executors.newFixedThreadPool(8);
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

    public F1MerchStore getMerchStore() {
        return merchStore;
    }

    public void setMerchStore(F1MerchStore merchStore) {
        this.merchStore = merchStore;
    }

    public ExecutorService getThreadPool() {
        return threadPool;
    }

    public void addCustomer(Customer customer) {
        Future<?> future = threadPool.submit(new RegisterCustomerRequest(customers, customer));

        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
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
        Future<?> future = threadPool.submit(new PurchaseTicketRequest(customer, race, seat));

        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}