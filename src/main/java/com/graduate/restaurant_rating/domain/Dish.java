package com.graduate.restaurant_rating.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * Created by Johann Stolz 14.08.2018
 */
@Entity
@Table(name = "dishes")
public class Dish extends AbstractBaseEntity {
    private LocalDate date = LocalDate.now();
    private Vote votes;
    private Restaurant restaurant;
    private double price;

    public Dish() {
    }

    public Dish(Integer id, LocalDate date, Vote votes, Restaurant restaurant, double price) {
        super(id);
        this.date = date;
        this.votes = votes;
        this.restaurant = restaurant;
        this.price = price;
    }
    public Dish (Dish dish){
        this(dish.id, dish.date, dish.votes, dish.restaurant, dish.price);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Vote getVotes() {
        return votes;
    }

    public void setVotes(Vote votes) {
        this.votes = votes;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "date=" + date +
                ", votes=" + votes +
                ", restaurant=" + restaurant +
                ", price=" + price +
                ", id=" + id +
                '}';
    }
}

