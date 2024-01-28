package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Customer {
    private static AtomicInteger customersCounter = new AtomicInteger(0);
    private int customerId;
    private String name;
    private String email;
    private List<Ticket> purchasedTickets;

    public Customer(String name, String email) {
        this.customerId = customersCounter.getAndIncrement();
        this.name = name;
        this.email = email;
        this.purchasedTickets = new ArrayList<>();
    }

    public List<Ticket> getPurchasedTickets() {
        return purchasedTickets;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", purchasedTickets=" + purchasedTickets +
                '}';
    }
}