package com.graduate.restaurant_rating.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Created by Johann Stolz 14.08.2018
 */

@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Dish dish;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    private LocalDateTime date;

    public Vote() {
    }

    public Vote(User user, Dish dish, Restaurant restaurant, LocalDateTime date) {
        this.user = user;
        this.dish = dish;
        this.restaurant = restaurant;
        this.date = date;
    }

    public Vote(Integer id, User user, Dish dish, Restaurant restaurant, LocalDateTime date) {
        super(id);
        this.user = user;
        this.dish = dish;
        this.restaurant = restaurant;
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", user=" + user +
                ", dish=" + dish +
                ", restaurant=" + restaurant +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Vote vote = (Vote) o;
        return Objects.equals(getUser(), vote.getUser()) &&
                Objects.equals(getDish(), vote.getDish()) &&
                Objects.equals(getRestaurant(), vote.getRestaurant()) &&
                Objects.equals(getDate(), vote.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getUser(), getDish(), getRestaurant(), getDate());
    }
}