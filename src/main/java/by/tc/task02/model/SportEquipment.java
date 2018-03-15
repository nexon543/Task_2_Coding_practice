package by.tc.task02.model;

import java.util.Objects;

public class SportEquipment {
    private Category category;
    private String title;
    private int price;
    private String renter;

    public String getRenter() {
        return renter;
    }

    @Override
    public String toString() {
        return "category=" + category +
                "; title=" + title +
                "; price=" + price +
                "; renter=" + renter;
    }

    public void setRenter(String renter) {
        this.renter = renter;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportEquipment that = (SportEquipment) o;
        return price == that.price &&
                category == that.category &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {

        return Objects.hash(category, title, price);
    }
}
