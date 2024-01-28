package store;


import domain.Customer;
import domain.Item;
import domain.ItemPurchaseVisitor;
import domain.ItemVisitor;
import exceptions.ItemNotAvailable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static store.F1TicketStore.threadPool;

public class F1MerchStore {
    private List<Item> items;
    private static F1MerchStore instance;
    private final Object purchaseLock = new Object();

    private F1MerchStore() {
        items = new ArrayList<>();
    }

    public static synchronized F1MerchStore getInstance() {
        if (instance == null) {
            instance = new F1MerchStore();
        }
        return instance;
    }

    public void addItemToStore(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public void purchaseItemFromMerchStore(Customer customer, Item item) {
        threadPool.submit(() -> {
            try {
                purchaseItem(customer, item);
            } catch (ItemNotAvailable e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void purchaseItem(Customer customer, Item item) throws ItemNotAvailable {
        Optional<Item> it = items.stream().filter(item1 -> item1.equals(item)).findFirst();
        if (it.isPresent()) {
            synchronized (purchaseLock) {
                Item existingItem = it.get();
                if (existingItem.getQuantity() >= item.getQuantity()) {
                    ItemVisitor visitor = new ItemPurchaseVisitor();
                    item.accept(visitor, customer, existingItem);
                } else {
                    throw new ItemNotAvailable("Not enough items in stock to match the requested quantity.");
                }
            }
        } else {
            throw new ItemNotAvailable("We don't have that item in our store.");
        }
    }

}