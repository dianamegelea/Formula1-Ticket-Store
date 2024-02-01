package domain;

public class Seat {
    private int row;
    private int seatNumber;
    private boolean available;
    private double price;
    private Category category;
    private String grandstandName;

    public Seat(Category category, double price) {
        this.row = -1;
        this.seatNumber = -1;
        this.available = true;
        this.price = price;
        this.category = category;
    }

    public Seat(Category category, String grandstandName, int row,
                int seatNumber, double price) {
        this.row = row;
        this.seatNumber = seatNumber;
        this.available = true;
        this.price = price;
        this.category = category;
        this.grandstandName = grandstandName;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        if (category == Category.GRANDSTAND) {
            return "Seat{" +
                    "price=" + price +
                    ", category=" + category +
                    ", grandstandName=" + grandstandName +
                    ", row=" + row +
                    ", seatNumber=" + seatNumber +
                    '}';
        } else {
            return "Seat{" +
                    "price=" + price +
                    ", category=" + category +
                    '}';
        }
    }
}
