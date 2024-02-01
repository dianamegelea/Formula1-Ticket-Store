package main;

import domain.*;
import exceptions.RaceDoesNotExistException;
import report.StoreReport;
import store.F1MerchStore;
import store.F1TicketStore;
import utils.Triple;

import java.util.*;

public class Main {
    public static void main(String[] args) throws RaceDoesNotExistException {
        F1TicketStore f1TicketStore = new F1TicketStore();
        F1MerchStore f1MerchStore = new F1MerchStore(f1TicketStore.getThreadPool());
        f1TicketStore.setMerchStore(f1MerchStore);

        F1Calendar f1Calendar = new F1Calendar();
        f1TicketStore.setRaces(f1Calendar.getAllRaces());

        F1Race MonzaGP = f1Calendar.getRace("Monza");
        F1Race MonacoGP = f1Calendar.getRace("Monaco");
        F1Race MiamiGP = f1Calendar.getRace("Miami");

        Seat generalAdmission = new Seat(Category.GENERAL_ADMISSION, 120);
        Seat grandstand26A = new Seat(Category.GRANDSTAND, "26A", 1, 1, 600);
        Seat grandstand1 = new Seat(Category.GRANDSTAND, "1", 1, 1, 800);
        if (MonzaGP != null) {
            f1TicketStore.addAvailableSeatsToRace(MonzaGP, List.of(generalAdmission, grandstand1, grandstand26A));
        } else {
            throw new RaceDoesNotExistException();
        }

        Seat paddock = new Seat(Category.PADDOCK, 5000);
        Seat grandstandB = new Seat(Category.GRANDSTAND, "B", 1, 1, 1400);
        if (MonacoGP != null) {
            f1TicketStore.addAvailableSeatsToRace(MonacoGP, List.of(paddock, grandstandB));
        } else {
            throw new RaceDoesNotExistException();
        }

        Seat paddockVIP = new Seat(Category.PADDOCK, 10000);
        Seat grandstandMain = new Seat(Category.GRANDSTAND, "Main", 1, 1, 1800);
        if (MiamiGP != null) {
            f1TicketStore.addAvailableSeatsToRace(MiamiGP, List.of(paddockVIP, grandstandMain));
        } else {
            throw new RaceDoesNotExistException();
        }

        Customer DianaMegelea = new Customer("Diana Megelea", "megeleadiana@gmail.com");
        Customer RaduNichita = new Customer("Radu Nichita", "nichitaradu@gmail.com");
        Customer CristiOlaru = new Customer("Cristian Olaru", "olarucristian@gmail.com");
        f1TicketStore.addCustomer(DianaMegelea);
        f1TicketStore.addCustomer(RaduNichita);
        f1TicketStore.addCustomer(CristiOlaru);

        f1TicketStore.purchaseTicket(DianaMegelea, MonacoGP, paddock);
        f1TicketStore.purchaseTicket(DianaMegelea, MiamiGP, paddockVIP);
        f1TicketStore.purchaseTicket(RaduNichita, MonzaGP, grandstand26A);
        f1TicketStore.purchaseTicket(CristiOlaru, MonzaGP, generalAdmission);

        List<Item> items = new ArrayList<>(Arrays.asList(
                new Cap("Ferrari", "red", 2024, 10),
                new Cap("Mercedes", "black", 2024, 1),
                new Cap("McLaren", "orange", 2024, 10),
                new Cap("RedBull", "dark blue", 2024, 10),
                new TShirt("Ferrari", "red", 2024, 34, "normal", "F", 5),
                new TShirt("Ferrari", "red", 2024, 40, "normal", "M", 2),
                new TShirt("Mercedes", "black", 2024, 34, "fit", "F", 5),
                new TShirt("Mercedes", "black", 2024, 38, "fit", "F", 3),
                new TShirt("Mercedes", "black", 2024, 42, "fit", "M", 5),
                new TShirt("McLaren", "orange", 2024, 36, "normal", "M", 5),
                new TShirt("McLaren", "orange", 2024, 40, "normal", "M", 7)
        ));
        items.forEach(f1MerchStore::addItemToStore);

        f1MerchStore.purchaseItemFromMerchStore(DianaMegelea, new TShirt("Ferrari", "red", 2024, 34, "normal", "F", 1));
        f1MerchStore.purchaseItemFromMerchStore(CristiOlaru, new Cap("Mercedes", "black", 2024, 1));

        StoreReport storeReport = new StoreReport(f1TicketStore);

        System.out.println("The seats available right now: ");
        storeReport.getAllAvailableSeats().forEach((name, seats) -> {
            System.out.println("\tRace: " + name);
            System.out.print("\t\tAvailable seats:");

            seats.forEach(value -> System.out.print("  " + value));
            System.out.println();
        });
        System.out.println();
        System.out.println("Tickets bought: ");
        storeReport.getAllTicketsBought().forEach((name, tickets) -> {
            System.out.println("\tCustomer: " + name);
            System.out.print("\t\tTickets:");

            tickets.forEach(value -> System.out.print("  " + value));
            System.out.println();
        });
        System.out.println();
        System.out.println("Number of tickets sold per race: " + storeReport.getNumberOfSoldTicketsPerRace());
        System.out.println();
        System.out.println("Items purchased from the merch store:");
        storeReport.getPurchasedItems().forEach((name, itemsBought) -> {
            System.out.println("\tCustomer: " + name);
            System.out.print("\t\tItems:");

            itemsBought.forEach(value -> System.out.print("  " + value));
            System.out.println();
        });
        System.out.println();
        System.out.println("Available items in store: ");
        storeReport.getAvailableItemsInStore().forEach(item -> System.out.println("\t" + item));
        System.out.println();
        System.out.println("Overall race report:");
        List<Triple<String, Integer, List<String>>> raceInfoList = storeReport.getRaceTicketInfoList();
        for (Triple<String, Integer, List<String>> triple : raceInfoList) {
            System.out.println("\tRace: " + triple.getFirst());
            System.out.println("\t\tSold Tickets: " + triple.getSecond());
            System.out.print("\t\tCustomers: ");

            List<String> customers = triple.getThird();
            for (String customer : customers) {
                System.out.print(customer + ", ");
            }
            System.out.println();
        }
    }
}