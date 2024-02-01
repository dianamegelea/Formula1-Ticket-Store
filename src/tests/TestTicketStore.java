package tests;

import domain.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import report.StoreReport;
import store.F1TicketStore;
import utils.Triple;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTicketStore {
    @Test
    public void test1AddRace() {
        F1TicketStore ticketStore = new F1TicketStore();
        ticketStore.addRaceToCalendar(new F1Race(1, "Bahrain", "Bahrain",
                new Date(2024, Calendar.MARCH, 2), 50000));

        assertEquals(1, ticketStore.getRaces().size());

        assertEquals("Bahrain", ticketStore.getRaces().get(0).getRaceName());
    }

    @Test
    public void test2AddSeats() {
        F1TicketStore ticketStore = new F1TicketStore();

        F1Race Bahrain = new F1Race(1, "Bahrain", "Bahrain",
                new Date(2024, Calendar.MARCH, 2), 50000);
        ticketStore.addRaceToCalendar(Bahrain);

        Seat generalAdmission = new Seat(Category.GENERAL_ADMISSION, 120);
        Seat paddock = new Seat(Category.PADDOCK, 3000);

        ticketStore.addAvailableSeatsToRace(Bahrain, List.of(generalAdmission, paddock));

        assertEquals(2, ticketStore.getRaces().get(0).getSeats().size());
    }

    @Test
    public void test3AddCustomers() {
        F1TicketStore ticketStore = new F1TicketStore();

        ticketStore.addCustomer(new Customer("Diana Megelea",
                "megeleadiana@gmail.com"));
        ticketStore.addCustomer(new Customer("Tiberiu Megelea",
                "megeleatiberiu@gmail.com"));

        assertEquals(2, ticketStore.getCustomers().size());

        assertEquals("Diana Megelea",
                ticketStore.getCustomers().get(0).getName());
        assertEquals("Tiberiu Megelea",
                ticketStore.getCustomers().get(1).getName());
    }

    @Test
    public void test4PurchaseTicket() {
        F1TicketStore ticketStore = new F1TicketStore();

        F1Race Bahrain = new F1Race(1, "Bahrain", "Bahrain",
                new Date(2024, Calendar.MARCH, 2), 50000);
        ticketStore.addRaceToCalendar(Bahrain);

        Seat generalAdmission = new Seat(Category.GENERAL_ADMISSION, 120);
        Seat paddock = new Seat(Category.PADDOCK, 3000);

        ticketStore.addAvailableSeatsToRace(Bahrain,
                List.of(generalAdmission, paddock));

        Customer Tiberiu = new Customer("Tiberiu Megelea",
                "megeleatiberiu@gmail.com");
        ticketStore.addCustomer(Tiberiu);

        ticketStore.purchaseTicket(Tiberiu, Bahrain, paddock);

        assertEquals(1, Tiberiu.getPurchasedTickets().size());
        assertEquals("Bahrain",
                Tiberiu.getPurchasedTickets().get(0).getRaceName());
        assertEquals(Category.PADDOCK,
                Tiberiu.getPurchasedTickets().get(0).getSeat().getCategory());
    }

    @Test
    public void test5GetAllAvailableSeats() {
        F1TicketStore ticketStore = new F1TicketStore();

        F1Race Bahrain = new F1Race(1, "Bahrain", "Bahrain",
                new Date(2024, Calendar.MARCH, 2), 50000);
        ticketStore.addRaceToCalendar(Bahrain);

        Seat generalAdmission = new Seat(Category.GENERAL_ADMISSION, 120);
        Seat paddock = new Seat(Category.PADDOCK, 3000);

        ticketStore.addAvailableSeatsToRace(Bahrain,
                List.of(generalAdmission, paddock));

        StoreReport storeReport = new StoreReport(ticketStore);
        Map<String, List<Seat>> map = storeReport.getAllAvailableSeats();

        assertEquals(1, map.size());
        assertEquals(2, map.get("Bahrain").size());
    }

    @Test
    public void test6GetAllTicketsBought() {
        F1TicketStore ticketStore = new F1TicketStore();

        F1Race Bahrain = new F1Race(1, "Bahrain", "Bahrain",
                new Date(2024, Calendar.MARCH, 2), 50000);
        ticketStore.addRaceToCalendar(Bahrain);

        Seat generalAdmission = new Seat(Category.GENERAL_ADMISSION, 120);
        Seat paddock = new Seat(Category.PADDOCK, 3000);

        ticketStore.addAvailableSeatsToRace(Bahrain,
                List.of(generalAdmission, paddock));

        Customer Tiberiu = new Customer("Tiberiu Megelea",
                "megeleatiberiu@gmail.com");
        ticketStore.addCustomer(Tiberiu);

        ticketStore.purchaseTicket(Tiberiu, Bahrain, paddock);

        StoreReport storeReport = new StoreReport(ticketStore);
        Map<String, List<Ticket>> map = storeReport.getAllTicketsBought();

        assertEquals(1, map.size());
        assertEquals(1, map.get("Tiberiu Megelea").size());
        assertEquals("Bahrain", map.get("Tiberiu Megelea").get(0).getRaceName());
    }

    @Test
    public void test7GetNumberOfSoldTicketsPerRace() {
        F1TicketStore ticketStore = new F1TicketStore();

        F1Race Bahrain = new F1Race(1, "Bahrain", "Bahrain",
                new Date(2024, Calendar.MARCH, 2), 50000);
        F1Race Miami = new F1Race(6, "Miami", "USA",
                new Date(2024, Calendar.MAY, 5), 70000);
        ticketStore.addRaceToCalendar(Bahrain);
        ticketStore.addRaceToCalendar(Miami);

        Seat generalAdmission = new Seat(Category.GENERAL_ADMISSION, 120);
        Seat paddock = new Seat(Category.PADDOCK, 3000);

        ticketStore.addAvailableSeatsToRace(Bahrain,
                List.of(generalAdmission, paddock));

        Seat paddockVIP = new Seat(Category.PADDOCK, 10000);
        Seat grandstandMain = new Seat(Category.GRANDSTAND, "Main", 1, 1, 1800);
        ticketStore.addAvailableSeatsToRace(Miami, List.of(paddockVIP, grandstandMain));


        Customer Tiberiu = new Customer("Tiberiu Megelea",
                "megeleatiberiu@gmail.com");
        Customer DianaMegelea = new Customer("Diana Megelea", "megeleadiana@gmail.com");
        ticketStore.addCustomer(DianaMegelea);
        ticketStore.addCustomer(Tiberiu);

        ticketStore.purchaseTicket(Tiberiu, Bahrain, paddock);
        ticketStore.purchaseTicket(DianaMegelea, Miami, paddockVIP);

        StoreReport storeReport = new StoreReport(ticketStore);
        Map<String, Integer> map = storeReport.getNumberOfSoldTicketsPerRace();

        assertEquals(2, map.size());
        assertEquals(1, map.get("Bahrain").intValue());
    }

    @Test
    public void test8GetRaceTicketInfoList() {
        F1TicketStore ticketStore = new F1TicketStore();

        F1Race Bahrain = new F1Race(1, "Bahrain", "Bahrain",
                new Date(2024, Calendar.MARCH, 2), 50000);
        F1Race Miami = new F1Race(6, "Miami", "USA",
                new Date(2024, Calendar.MAY, 5), 70000);
        ticketStore.addRaceToCalendar(Bahrain);
        ticketStore.addRaceToCalendar(Miami);

        Seat generalAdmission = new Seat(Category.GENERAL_ADMISSION, 120);
        Seat paddock = new Seat(Category.PADDOCK, 3000);

        ticketStore.addAvailableSeatsToRace(Bahrain,
                List.of(generalAdmission, paddock));

        Seat paddockVIP = new Seat(Category.PADDOCK, 10000);
        Seat grandstandMain = new Seat(Category.GRANDSTAND, "Main", 1, 1, 1800);
        ticketStore.addAvailableSeatsToRace(Miami, List.of(paddockVIP, grandstandMain));


        Customer Tiberiu = new Customer("Tiberiu Megelea",
                "megeleatiberiu@gmail.com");
        Customer DianaMegelea = new Customer("Diana Megelea", "megeleadiana@gmail.com");
        ticketStore.addCustomer(DianaMegelea);
        ticketStore.addCustomer(Tiberiu);

        ticketStore.purchaseTicket(Tiberiu, Bahrain, paddock);
        ticketStore.purchaseTicket(DianaMegelea, Miami, paddockVIP);

        StoreReport storeReport = new StoreReport(ticketStore);
        List<Triple<String, Integer, List<String>>> list = storeReport.getRaceTicketInfoList();

        assertEquals(2, list.size());
        assertEquals("Bahrain", list.get(0).getFirst());
        assertEquals(1, list.get(0).getSecond().intValue());
        assertEquals("Tiberiu Megelea", list.get(0).getThird().get(0));
    }
}
