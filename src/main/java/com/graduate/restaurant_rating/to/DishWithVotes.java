package com.graduate.restaurant_rating.to;

import com.graduate.restaurant_rating.HasId;

import java.time.LocalDate;
import java.util.Objects;


public class DishWithVotes implements HasId {
    protected Integer id;
    private long countOfVotes;
    private LocalDate date;

    public DishWithVotes() {
    }

    public DishWithVotes(Integer id, long countOfVotes) {
        this.id = id;
        this.countOfVotes = countOfVotes;
    }

    public DishWithVotes(Integer dishId, LocalDate date, long countOfVotes) {
        this.id = dishId;
        this.countOfVotes = countOfVotes;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishWithVotes that = (DishWithVotes) o;
        return getCountOfVotes() == that.getCountOfVotes() &&
                Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCountOfVotes());
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

    public void setDateTime(LocalDate date) {
        this.date = date;
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
