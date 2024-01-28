package report;

import domain.Category;
import domain.Seat;
import store.F1TicketStore;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StoreReport {
    private final F1TicketStore store;

    public StoreReport(F1TicketStore store) {
        this.store = store;
    }

    public F1TicketStore getStore() {
        return store;
    }
    public Map<Category, List<Seat>> getAllAvailableSeats() {
        return store.getRaces().stream()
                .flatMap(raceDetails -> raceDetails.getSeats().stream())
                .filter(Seat::isAvailable)
                .collect(Collectors.groupingBy(Seat::getCategory));
    }
}
