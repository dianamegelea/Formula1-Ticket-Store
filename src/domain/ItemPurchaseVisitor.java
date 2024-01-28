package domain;

import exceptions.ItemNotAvailable;

public class ItemPurchaseVisitor implements ItemVisitor {
    @Override
    public void visit(TShirt tShirt, Customer customer, Item existingItem) throws ItemNotAvailable {
        TShirt storedTShirt = (TShirt) existingItem;

        if (storedTShirt.getSize() == tShirt.getSize() && storedTShirt.getGender().equals(tShirt.getGender())) {
            existingItem.decreaseQuantity(tShirt.getQuantity());
            customer.getPurchasedMerch().add(tShirt);
        } else {
            throw new ItemNotAvailable("The requested size is not available for this T-Shirt.");
        }
    }

    @Override
    public void visit(Cap cap, Customer customer, Item existingItem) throws ItemNotAvailable {
        existingItem.decreaseQuantity(cap.getQuantity());
        customer.getPurchasedMerch().add(cap);
    }
}

