package domain;

import exceptions.ItemNotAvailable;

public class Cap extends Item {
    public Cap(String team, String color, int season, int quantity) {
        super(team, color, season, quantity);
    }

    @Override
    public void accept(ItemVisitor visitor, Customer customer, Item existingItem) throws ItemNotAvailable {
        visitor.visit(this, customer, existingItem);
    }

    @Override
    public String toString() {
        return "Cap{" +
                super.toString() +
                '}';
    }
}
