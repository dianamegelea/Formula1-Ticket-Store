package domain;

import exceptions.ItemNotAvailable;

public interface ItemVisitor {
    void visit(TShirt tShirt, Customer customer, Item existingItem) throws ItemNotAvailable;
    void visit(Cap cap, Customer customer, Item existingItem) throws ItemNotAvailable;
}

