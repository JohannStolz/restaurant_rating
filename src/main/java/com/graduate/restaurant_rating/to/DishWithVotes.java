package com.graduate.restaurant_rating.to;

import com.graduate.restaurant_rating.HasId;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Created by Johann Stolz 06.09.2018
 */
public class DishWithVotes implements HasId {
    protected Integer id;
    private String description;
    private LocalDate date;
    private int restaurantId;
    private double price;
    private long countOfVotes;

    public DishWithVotes() {
    }

    public DishWithVotes(Integer id, String description, LocalDate date, int restaurantId, double price, long countOfVotes) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.restaurantId = restaurantId;
        this.price = price;
        this.countOfVotes = countOfVotes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getCountOfVotes() {
        return countOfVotes;
    }

    public void setCountOfVotes(long countOfVotes) {
        this.countOfVotes = countOfVotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishWithVotes that = (DishWithVotes) o;
        return getRestaurantId() == that.getRestaurantId() &&
                Double.compare(that.getPrice(), getPrice()) == 0 &&
                getCountOfVotes() == that.getCountOfVotes() &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), getDate(), getRestaurantId(), getPrice(), getCountOfVotes());
    }

    @Override
    public String toString() {
        return "DishWithVotes{" +
                "description='" + description + '\'' +
                ", date=" + date +
                ", restaurantId=" + restaurantId +
                ", price=" + price +
                ", countOfVotes=" + countOfVotes +
                '}';
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }
}
