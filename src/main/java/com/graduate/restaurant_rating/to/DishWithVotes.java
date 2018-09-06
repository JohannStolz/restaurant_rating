package com.graduate.restaurant_rating.to;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Created by Johann Stolz 06.09.2018
 */
public class DishWithVotes {

    private String description;
    private LocalDate date;
    private int restaurantId;
    private double price;
    private int voteId;

    public DishWithVotes() {
    }

    public DishWithVotes(String description, LocalDate date, int restaurantId, double price, int voteId) {
        this.description = description;
        this.date = date;
        this.restaurantId = restaurantId;
        this.price = price;
        this.voteId = voteId;
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

    public int getVoteId() {
        return voteId;
    }

    public void setVoteId(int voteId) {
        this.voteId = voteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishWithVotes that = (DishWithVotes) o;
        return getRestaurantId() == that.getRestaurantId() &&
                Double.compare(that.getPrice(), getPrice()) == 0 &&
                getVoteId() == that.getVoteId() &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), getDate(), getRestaurantId(), getPrice(), getVoteId());
    }

    @Override
    public String toString() {
        return "DishWithVotes{" +
                "description='" + description + '\'' +
                ", date=" + date +
                ", restaurantId=" + restaurantId +
                ", price=" + price +
                ", voteId=" + voteId +
                '}';
    }
}
