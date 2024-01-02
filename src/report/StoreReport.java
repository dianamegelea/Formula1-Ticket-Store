package report;

import domain.Ticket;
import store.F1TicketStore;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StoreReport {
    private F1TicketStore store;

    public StoreReport(F1TicketStore store) {
        this.store = store;
    }

    public F1TicketStore getStore() {
        return store;
    }
    public Map<String, List<Ticket>> getAllAvailableTickets() {
        return store.getRaces().stream()
                .flatMap(raceDetails -> raceDetails.getAvailableTickets().stream())
                .collect(Collectors.groupingBy(Ticket::getRaceName));
    }
}
