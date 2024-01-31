package domain;

import exceptions.ItemNotAvailable;

public class ItemPurchaseVisitor implements ItemVisitor {
    @Override
    public void visit(TShirt tShirt, Customer customer, Item existingItem)
            throws ItemNotAvailable {
        TShirt storedTShirt = (TShirt) existingItem;

        if (storedTShirt.getSize() == tShirt.getSize()
                && storedTShirt.getGender().equals(tShirt.getGender())) {
            if (existingItem.decreaseQuantity(tShirt.getQuantity())) {
                customer.getPurchasedMerch().add(tShirt);
            } else {
                throw new ItemNotAvailable("The requested number of items " +
                        "is too big, we don't have so many in stock.");
            }
        } else {
            throw new ItemNotAvailable("The requested size is not available" +
                    " for this T-Shirt.");
        }
    }

    @Override
    public void visit(Cap cap, Customer customer, Item existingItem)
            throws ItemNotAvailable {
        if (existingItem.decreaseQuantity(cap.getQuantity())) {
            customer.getPurchasedMerch().add(cap);
        } else {
            throw new ItemNotAvailable("The requested number of items is " +
                    "too big, we don't have so many in stock.");
        }
    }
}

