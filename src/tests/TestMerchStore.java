package tests;

import domain.Cap;
import domain.Customer;
import domain.Item;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import report.StoreReport;
import store.F1MerchStore;
import store.F1TicketStore;

import java.util.List;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestMerchStore {
    @Test
    public void test1AddMerch() {
        F1TicketStore ticketStore = new F1TicketStore();
        F1MerchStore merchStore = new F1MerchStore(ticketStore.getThreadPool());
        ticketStore.setMerchStore(merchStore);

        merchStore.addItemToStore(new Cap("Mercedes", "black", 2024, 1));

        assertEquals(1, merchStore.getItems().size());
    }

    @Test
    public void test2PurchaseItem() {
        F1TicketStore ticketStore = new F1TicketStore();
        F1MerchStore merchStore = new F1MerchStore(ticketStore.getThreadPool());
        ticketStore.setMerchStore(merchStore);

        Item cap = new Cap("Ferrari", "red", 2023, 1);
        merchStore.addItemToStore(cap);

        Customer Tiberiu = new Customer("Tiberiu Megelea",
                "megeleatiberiu@gmail.com");
        ticketStore.addCustomer(Tiberiu);

        merchStore.purchaseItemFromMerchStore(Tiberiu, cap);

        assertEquals(1, Tiberiu.getPurchasedMerch().size());
    }

    @Test
    public void test3GetAvailableItems() {
        F1TicketStore ticketStore = new F1TicketStore();
        F1MerchStore merchStore = new F1MerchStore(ticketStore.getThreadPool());
        ticketStore.setMerchStore(merchStore);

        Item cap = new Cap("Ferrari", "red", 2023, 5);
        merchStore.addItemToStore(cap);

        Item cap1 = new Cap("Ferrari", "red", 2023, 1);

        Customer Tiberiu = new Customer("Tiberiu Megelea",
                "megeleatiberiu@gmail.com");
        ticketStore.addCustomer(Tiberiu);

        merchStore.purchaseItemFromMerchStore(Tiberiu, cap1);

        StoreReport storeReport = new StoreReport(ticketStore);
        List<Item> list = storeReport.getAvailableItemsInStore();

        assertEquals(1, list.size());
        assertEquals(4, list.get(0).getQuantity());
    }
}
