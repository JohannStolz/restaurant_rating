package com.graduate.restaurant_rating.to;

import com.graduate.restaurant_rating.domain.Restaurant;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Created by Johann Stolz 14.08.2018
 */
public class VoteWinner {
    private LocalDate date;
    private Restaurant restaurant;
    private boolean isWinner;

    public VoteWinner(LocalDate date, Restaurant restaurant, boolean isWinner) {
        this.date = date;
        this.restaurant = restaurant;
        this.isWinner = isWinner;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteWinner that = (VoteWinner) o;
        return isWinner() == that.isWinner() &&
                Objects.equals(getDate(), that.getDate()) &&
                Objects.equals(getRestaurant(), that.getRestaurant());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getRestaurant(), isWinner());
    }
}
