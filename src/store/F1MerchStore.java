package store;


import domain.Customer;
import domain.Item;
import domain.ItemPurchaseVisitor;
import domain.ItemVisitor;
import exceptions.ItemNotAvailable;
import request.PurchaseItemRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class F1MerchStore {
    private List<Item> items;
    private ExecutorService threadPool;

    public F1MerchStore(ExecutorService threadPool) {
        items = Collections.synchronizedList(new ArrayList<>());
        this.threadPool = threadPool;
    }

    public void addItemToStore(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public void purchaseItemFromMerchStore(Customer customer, Item item) {
        Future<?> future = threadPool.submit(new PurchaseItemRequest(items, customer, item));

        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
