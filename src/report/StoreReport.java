package report;

import domain.*;
import store.F1MerchStore;
import store.F1TicketStore;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

public class StoreReport {
    private final F1TicketStore ticketStore;
    private final F1MerchStore merchStore;

    public StoreReport(F1TicketStore ticketStore) {
        this.ticketStore = ticketStore;
        this.merchStore = ticketStore.getMerchStore();
    }

    public F1TicketStore getTicketStore() {
        return ticketStore;
    }

    public F1MerchStore getMerchStore() {
        return merchStore;
    }

    public Map<String, List<Seat>> getAllAvailableSeats() {
        return ticketStore.getRaces().stream()
                .flatMap(raceDetails -> raceDetails.getSeats().stream()
                        .filter(Seat::isAvailable)
                        .map(seat -> new AbstractMap.SimpleEntry<>(raceDetails.getRaceName(), seat)))
                .collect(Collectors.groupingBy(Map.Entry::getKey, mapping(Map.Entry::getValue, toList())));
    }

    public Map<String, List<Ticket>> getAllTicketsBought() {
        return ticketStore.getCustomers().stream()
                .flatMap(customer -> customer.getPurchasedTickets().stream()
                        .map(ticket -> new AbstractMap.SimpleEntry<>(customer.getName(), ticket)))
                .collect(Collectors.groupingBy(Map.Entry::getKey, mapping(Map.Entry::getValue, Collectors.toList())));
    }

    public Map<String, Integer> getNumberOfSoldTicketsPerRace() {
        return ticketStore.getRaces().stream()
                .filter(race -> race.getSoldTickets() > 0)
                .collect(Collectors.toMap(F1Race::getRaceName, F1Race::getSoldTickets));
    }

    public Map<String, List<Item>> getPurchasedItems() {
        return ticketStore.getCustomers().stream()
                .collect(Collectors.toMap(Customer::getName, Customer::getPurchasedMerch));
    }

    public List<Item> getAvailableItemsInStore() {
        return merchStore.getItems().stream().filter(item -> item.getQuantity() > 0).collect(toList());
    }
}
