package domain;

import exceptions.ItemNotAvailable;
import store.F1MerchStore;

import java.util.Objects;

public abstract class Item {
    private String team;
    private String color;

    private int season;
    private int quantity;

    public Item(String team, String color, int season, int quantity) {
        this.team = team;
        this.color = color;
        this.season = season;
        this.quantity = quantity;
    }

    public abstract void accept(ItemVisitor visitor, Customer customer, Item existingItem) throws ItemNotAvailable;

    public int getQuantity() {
        return quantity;
    }

    public boolean decreaseQuantity(int q) {
        if (quantity >= q) {
            quantity -= q;
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return season == item.season && Objects.equals(team, item.team) && Objects.equals(color, item.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, color, season);
    }

    @Override
    public String toString() {
        return '{' +
                "team=" + team +
                ", color=" + color +
                ", season=" + season +
                ", quantity=" + quantity;
    }
}

