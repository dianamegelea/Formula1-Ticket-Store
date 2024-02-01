package domain;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private String email;
    private List<Ticket> purchasedTickets;
    private List<Item> purchasedMerch;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
        this.purchasedTickets = new ArrayList<>();
        this.purchasedMerch = new ArrayList<>();
    }

    public List<Ticket> getPurchasedTickets() {
        return purchasedTickets;
    }

    public List<Item> getPurchasedMerch() {
        return purchasedMerch;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name=" + name +
                ", email=" + email +
                ", purchasedTickets=" + purchasedTickets +
                ", purchasedMerch=" + purchasedMerch +
                '}';
    }
}
