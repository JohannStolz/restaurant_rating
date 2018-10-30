package com.graduate.restaurant_rating.to;

import com.graduate.restaurant_rating.domain.Dish;

import java.time.LocalDate;
import java.util.Objects;


public class DishWithVotes {
    private Dish dish;
    private long countOfVotes;
    private LocalDate date;

    public DishWithVotes() {
    }

    public DishWithVotes(Dish dish, long countOfVotes) {
        this.dish = dish;
        this.countOfVotes = countOfVotes;
        this.date = dish.getDate();
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public long getCountOfVotes() {
        return countOfVotes;
    }

    public void setCountOfVotes(long countOfVotes) {
        this.countOfVotes = countOfVotes;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishWithVotes that = (DishWithVotes) o;
        return getCountOfVotes() == that.getCountOfVotes() &&
                Objects.equals(getDish(), that.getDish()) &&
                Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDish(), getCountOfVotes(), getDate());
    }
}
