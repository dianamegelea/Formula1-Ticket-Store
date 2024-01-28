package tests;

import domain.Category;
import domain.Customer;
import domain.F1Race;
import domain.Seat;
import org.junit.Test;
import store.F1TicketStore;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestTicketStore {
    @Test
    public void testAddRace() {
        F1TicketStore ticketStore = F1TicketStore.getInstance();
        ticketStore.addRaceToCalendar(new F1Race(1, "Bahrain", "Bahrain", new Date(2024, Calendar.MARCH, 2), 50000));

        assertEquals(1, ticketStore.getRaces().size());

        assertEquals("Bahrain", ticketStore.getRaces().get(0).getRaceName());
    }

    @Test
    public void testAddCustomer() {
        F1TicketStore ticketStore = F1TicketStore.getInstance();
        ticketStore.addRaceToCalendar(new F1Race(1, "Bahrain", "Bahrain", new Date(2024, Calendar.MARCH, 2), 50000));

        ticketStore.addCustomerAsync(new Customer("Diana Megelea", "megeleadiana@gmail.com"));
        ticketStore.addCustomerAsync(new Customer("Tiberiu Megelea", "megeleatiberiu@gmail.com"));

        assertEquals(2, ticketStore.getCustomers().size());

        assertEquals(0, ticketStore.getCustomers().get(0).getCustomerId());
        assertEquals(1, ticketStore.getCustomers().get(1).getCustomerId());
        assertEquals("Diana Megelea", ticketStore.getCustomers().get(0).getName());
        assertEquals("Tiberiu Megelea", ticketStore.getCustomers().get(1).getName());
    }

    @Test
    public void testAddSeats() {
        F1TicketStore ticketStore = F1TicketStore.getInstance();

        F1Race Bahrain = new F1Race(1, "Bahrain", "Bahrain", new Date(2024, Calendar.MARCH, 2), 50000);
        ticketStore.addRaceToCalendar(Bahrain);

        Seat generalAdmission = new Seat(Category.GENERAL_ADMISSION, 120);
        Seat paddock = new Seat(Category.PADDOCK, 3000);

        ticketStore.addAvailableSeatsToRace(Bahrain, List.of(generalAdmission, paddock));

        assertEquals(2, ticketStore.getRaces().get(0).getSeats().size());
    }

    @Test
    public void testPurchaseTicket() {
        F1TicketStore ticketStore = F1TicketStore.getInstance();

        F1Race Bahrain = new F1Race(1, "Bahrain", "Bahrain", new Date(2024, Calendar.MARCH, 2), 50000);
        ticketStore.addRaceToCalendar(Bahrain);

        Seat generalAdmission = new Seat(Category.GENERAL_ADMISSION, 120);
        Seat paddock = new Seat(Category.PADDOCK, 3000);

        ticketStore.addAvailableSeatsToRace(Bahrain, List.of(generalAdmission, paddock));

        Customer Tiberiu = new Customer("Tiberiu Megelea", "megeleatiberiu@gmail.com");
        ticketStore.addCustomerAsync(Tiberiu);

        ticketStore.purchaseTicketAsync(Tiberiu, Bahrain, paddock);

        assertEquals(1, Tiberiu.getPurchasedTickets().size());
        assertEquals("Bahrain", Tiberiu.getPurchasedTickets().get(0).getRaceName());
        assertEquals(Category.PADDOCK, Tiberiu.getPurchasedTickets().get(0).getSeat().getCategory());
    }
}
