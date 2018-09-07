package com.graduate.restaurant_rating.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

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

    private LocalDate date = LocalDate.now();

    public Vote() {
    }

    public Vote(User user, Dish dish, Restaurant restaurant, LocalDate date) {
        this.user = user;
        this.dish = dish;
        this.restaurant = restaurant;
        this.date = date;
    }

    public Vote(Integer id, User user, Dish dish, Restaurant restaurant, LocalDate date) {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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
}