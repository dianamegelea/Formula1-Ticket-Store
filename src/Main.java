import domain.*;
import exceptions.RaceDoesNotExistException;
import report.StoreReport;
import store.F1TicketStore;

import java.util.*;

public class Main {
    public static void main(String[] args) throws RaceDoesNotExistException {
        F1TicketStore f1TicketStore = F1TicketStore.getInstance();

        List<F1Race> races = createCalendar();
        f1TicketStore.setRaces(races);

        F1Calendar f1Calendar = new F1Calendar(races);
        Optional<F1Race> MonzaGP = f1Calendar.getRace("Monza");
        Optional<F1Race> MonacoGP = f1Calendar.getRace("Monaco");

        Seat generalAdmission = new Seat(Category.GENERAL_ADMISSION, 120);
        Seat grandstand26A = new Seat(Category.GRANDSTAND, "26A", 1, 1, 600);
        Seat grandstand1 = new Seat(Category.GRANDSTAND, "1", 1, 1, 800);

        if (MonzaGP.isPresent()) {
            f1TicketStore.addAvailableSeatsToRace(MonzaGP.get(), List.of(generalAdmission, grandstand1, grandstand26A));
        } else {
            throw new RaceDoesNotExistException();
        }

        Seat paddock = new Seat(Category.PADDOCK, 5000);
        Seat grandstandB = new Seat(Category.GRANDSTAND, "B", 1, 1, 1400);

        if (MonacoGP.isPresent()) {
            f1TicketStore.addAvailableSeatsToRace(MonacoGP.get(), List.of(paddock, grandstandB));
        } else {
            throw new RaceDoesNotExistException();
        }

        Customer DianaMegelea = new Customer("Diana Megelea", "megeleadiana@gmail.com");
        f1TicketStore.addCustomerAsync(DianaMegelea);

        f1TicketStore.purchaseTicket(DianaMegelea, MonacoGP.get(), paddock);

        StoreReport storeReport = new StoreReport(f1TicketStore);
        System.out.println(storeReport.getAllAvailableSeats());

        System.out.println(DianaMegelea);
    }

    private static List<F1Race> createCalendar() {
        List<F1Race> races = new ArrayList<>();

        races.add(new F1Race(1, "Bahrain", "Bahrain", new Date(2024, Calendar.MARCH, 2), 50000));
        races.add(new F1Race(2, "Jeddah", "Saudi Arabia", new Date(2024, Calendar.MARCH, 9), 70000));
        races.add(new F1Race(3, "Australia", "Australia", new Date(2024, Calendar.MARCH, 24), 90000));
        races.add(new F1Race(4, "Suzuka", "Japan", new Date(2024, Calendar.APRIL, 7), 100000));
        races.add(new F1Race(5, "China", "China", new Date(2024, Calendar.APRIL, 21), 70000));
        races.add(new F1Race(6, "Miami", "USA", new Date(2024, Calendar.MAY, 5), 70000));
        races.add(new F1Race(7, "Imola", "Italy", new Date(2024, Calendar.MAY, 19), 90000));
        races.add(new F1Race(8, "Monaco", "Monaco", new Date(2024, Calendar.MAY, 26), 60000));
        races.add(new F1Race(9, "Canada", "Canada", new Date(2024, Calendar.JUNE, 9), 75000));
        races.add(new F1Race(10, "Spain", "Spain", new Date(2024, Calendar.JUNE, 23), 80000));
        races.add(new F1Race(11, "Austria", "Austria", new Date(2024, Calendar.JUNE, 30), 70000));
        races.add(new F1Race(12, "Silverstone", "UK", new Date(2024, Calendar.JULY, 7), 90000));
        races.add(new F1Race(13, "Hungary", "Hungary", new Date(2024, Calendar.JULY, 21), 80000));
        races.add(new F1Race(14, "Spa", "Belgium", new Date(2024, Calendar.JULY, 28), 100000));
        races.add(new F1Race(15, "Zandvoort", "Netherlands", new Date(2024, Calendar.AUGUST, 25), 70000));
        races.add(new F1Race(16, "Monza", "Italy", new Date(2024, Calendar.SEPTEMBER, 1), 100000));
        races.add(new F1Race(17, "Baku", "Azerbaijan", new Date(2024, Calendar.SEPTEMBER, 15), 80000));
        races.add(new F1Race(18, "Singapore", "Singapore", new Date(2024, Calendar.SEPTEMBER, 22), 70000));
        races.add(new F1Race(19, "Austin", "USA", new Date(2024, Calendar.OCTOBER, 20), 90000));
        races.add(new F1Race(20, "Mexico", "Mexico", new Date(2024, Calendar.OCTOBER, 27), 80000));
        races.add(new F1Race(21, "Brazil", "Brazil", new Date(2024, Calendar.NOVEMBER, 3), 80000));
        races.add(new F1Race(22, "Las Vegas", "USA", new Date(2024, Calendar.NOVEMBER, 23), 70000));
        races.add(new F1Race(23, "Qatar", "Qatar", new Date(2024, Calendar.DECEMBER, 1), 70000));
        races.add(new F1Race(24, "Abu Dhabi", "UAE", new Date(2024, Calendar.MAY, 20), 70000));

        return races;
    }
}