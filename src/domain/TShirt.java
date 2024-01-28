package domain;

import exceptions.ItemNotAvailable;

public class TShirt extends Item {
    private int size;
    private String fit;
    private String gender;
    public TShirt(String team, String color, int season, int size, String fit, String gender, int quantity) {
        super(team, color, season, quantity);
        this.size = size;
        this.fit = fit;
        this.gender = gender;
    }

    @Override
    public void accept(ItemVisitor visitor, Customer customer, Item existingItem) throws ItemNotAvailable {
        visitor.visit(this, customer, existingItem);
    }

    public int getSize() {
        return size;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "TShirt{" +
                super.toString() +
                ", size=" + size +
                ", fit=" + fit +
                ", gender=" + gender +
                '}';
    }
}
