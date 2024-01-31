package tests;

import domain.Cap;
import domain.Customer;
import domain.Item;
import org.junit.Test;
import store.F1MerchStore;
import store.F1TicketStore;

import static org.junit.Assert.assertEquals;

public class TestMerchStore {
    @Test
    public void testAddMerch() {
        F1TicketStore ticketStore = F1TicketStore.getInstance();
        F1MerchStore merchStore = F1MerchStore.getInstance();
        ticketStore.setMerchStore(merchStore);

        merchStore.addItemToStore(new Cap("Mercedes", "black", 2024, 1));

        assertEquals(1, merchStore.getItems().size());
    }

    @Test
    public void testPurchaseItem() {
        F1TicketStore ticketStore = F1TicketStore.getInstance();
        F1MerchStore merchStore = F1MerchStore.getInstance();
        ticketStore.setMerchStore(merchStore);

        Item cap = new Cap("Ferrari", "red", 2024, 1);
        merchStore.addItemToStore(cap);

        Customer Tiberiu = new Customer("Tiberiu Megelea", "megeleatiberiu@gmail.com");
        ticketStore.addCustomer(Tiberiu);

        merchStore.purchaseItemFromMerchStore(Tiberiu, cap);

        assertEquals(1, Tiberiu.getPurchasedMerch().size());
    }
}
