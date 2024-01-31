package report;

import domain.*;
import store.F1MerchStore;
import store.F1TicketStore;
import utils.Triple;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StoreReport {
    private final F1TicketStore ticketStore;
    private final F1MerchStore merchStore;

    public StoreReport(F1TicketStore ticketStore) {
        this.ticketStore = ticketStore;
        this.merchStore = ticketStore.getMerchStore();
    }

    public Map<String, List<Seat>> getAllAvailableSeats() {
        return ticketStore.getRaces().stream()
                .flatMap(raceDetails -> raceDetails.getSeats().stream()
                        .filter(Seat::isAvailable)
                        .map(seat -> Map.entry(raceDetails.getRaceName(), seat)))
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue,
                                Collectors.toList())));
    }

    public Map<String, List<Ticket>> getAllTicketsBought() {
        return ticketStore.getCustomers().stream()
                .flatMap(customer -> customer.getPurchasedTickets().stream()
                        .map(ticket -> Map.entry(customer.getName(), ticket)))
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue,
                                Collectors.toList())));
    }

    public Map<String, Integer> getNumberOfSoldTicketsPerRace() {
        return ticketStore.getRaces().stream()
                .filter(race -> race.getSoldTickets() > 0)
                .collect(Collectors.toMap(F1Race::getRaceName,
                        F1Race::getSoldTickets));
    }

    public Map<String, List<Item>> getPurchasedItems() {
        return ticketStore.getCustomers().stream()
                .filter(customer -> !customer.getPurchasedMerch().isEmpty())
                .collect(Collectors.toMap(Customer::getName,
                        Customer::getPurchasedMerch));
    }

    public List<Item> getAvailableItemsInStore() {
        return merchStore.getItems().stream()
                .filter(item -> item.getQuantity() > 0)
                .collect(Collectors.toList());
    }

    public List<Triple<String, Integer, List<String>>> getRaceTicketInfoList() {
        Map<String, List<String>> raceToCustomerNamesMap =
                ticketStore.getCustomers().stream()
                .flatMap(customer -> customer.getPurchasedTickets().stream()
                        .map(ticket -> Map.entry(ticket.getRaceName(),
                                customer.getName())))
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue,
                                Collectors.toList())));

        return ticketStore.getRaces().stream()
                .filter(race -> race.getSoldTickets() > 0)
                .map(race -> new Triple<>(
                        race.getRaceName(),
                        race.getSoldTickets(),
                        raceToCustomerNamesMap.getOrDefault(
                                race.getRaceName(), null)))
                .collect(Collectors.toList());
    }
}
