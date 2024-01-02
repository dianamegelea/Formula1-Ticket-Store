import domain.Category;
import domain.Customer;
import domain.F1Race;
import domain.Ticket;
import report.StoreReport;
import store.F1TicketStore;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        F1TicketStore f1TicketStore = F1TicketStore.getInstance();

        F1Race MonzaGP = new F1Race(9, "Monza", "Italy", new Date(2024, Calendar.SEPTEMBER, 1), 100000);
        f1TicketStore.addRaceToCalendar(MonzaGP);

        Ticket generalAdmission = new Ticket("Monza", Category.GENERAL_ADMISSION, 120);
        Ticket generalAdmission2 = new Ticket("Monza", Category.GENERAL_ADMISSION, 110);
        Ticket grandstand26A = new Ticket("Monza", Category.GRANDSTAND, "26A", 1, 1, 600);
        Ticket grandstand1 = new Ticket("Monza", Category.GRANDSTAND, "1", 1, 1, 800);

        f1TicketStore.addTicketsToRace(MonzaGP, Category.GENERAL_ADMISSION, List.of(generalAdmission, generalAdmission2));
        f1TicketStore.addTicketsToRace(MonzaGP, Category.GRANDSTAND, List.of(grandstand1, grandstand26A));

        F1Race MonacoGP = new F1Race(5, "Monaco", "Monaco", new Date(2024, Calendar.MAY, 20), 70000);
        f1TicketStore.addRaceToCalendar(MonacoGP);

        Ticket paddock = new Ticket("Monaco", Category.PADDOCK, 4000);
        f1TicketStore.addTicketsToRace(MonacoGP, Category.PADDOCK, List.of(paddock));

        Customer DianaMegelea = new Customer("Diana Megelea", "megeleadiana@gmail.com");
        f1TicketStore.registerCustomer(DianaMegelea);
        f1TicketStore.purchaseTicket(DianaMegelea, MonacoGP, paddock);

        StoreReport storeReport = new StoreReport(f1TicketStore);
        System.out.println(storeReport.getAllAvailableTickets());

        System.out.println(DianaMegelea);
    }
}