package request;

import domain.*;
import exceptions.ItemNotAvailable;

import java.util.List;
import java.util.Optional;

public class PurchaseItemRequest implements Runnable {
    private List<Item> items;
    private Item item;
    private Customer customer;

    public PurchaseItemRequest(List<Item> items, Customer customer, Item item) {
        this.customer = customer;
        this.item = item;
        this.items = items;
    }

    @Override
    public void run() {
        Optional<Item> it = items.stream()
                .filter(item1 -> item1.equals(item)).findFirst();
        if (it.isPresent()) {
            synchronized (this) {
                Item existingItem = it.get();

                ItemVisitor visitor = new ItemPurchaseVisitor();
                try {
                    item.accept(visitor, customer, existingItem);
                } catch (ItemNotAvailable e) {
                    e.getLocalizedMessage();
                }
            }
        }
    }
}
