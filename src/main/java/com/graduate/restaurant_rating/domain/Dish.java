package com.graduate.restaurant_rating.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by Johann Stolz 14.08.2018
 */
@Entity
@Table(name = "dishes")
public class Dish extends AbstractBaseEntity {
    private String description;
    @NotNull
    private LocalDate date = LocalDate.now();
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    private double price;

    public Dish() {
    }

    public Dish(String description, @NotNull LocalDate date, Restaurant restaurant, double price) {
        this.description = description;
        this.date = date;
        this.restaurant = restaurant;
        this.price = price;
    }

    public Dish(Integer id, String description, @NotNull LocalDate date, Restaurant restaurant, double price) {
        super(id);
        this.description = description;
        this.date = date;
        this.restaurant = restaurant;
        this.price = price;
    }

    public Dish(Dish dish) {
        this(dish.id, dish.description, dish.date, dish.restaurant, dish.price);
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "description='" + description + '\'' +
                ", date=" + date +
                ", restaurant=" + restaurant +
                ", price=" + price +
                ", id=" + id +
                '}';
    }
}
