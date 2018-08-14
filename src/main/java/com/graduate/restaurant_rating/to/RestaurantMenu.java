package com.graduate.restaurant_rating.to;

import com.graduate.restaurant_rating.domain.Dish;
import com.graduate.restaurant_rating.domain.Restaurant;

import java.time.LocalDate;
import java.util.Set;

/**
 * Created by Johann Stolz 14.08.2018
 */

public class RestaurantMenu {
    private LocalDate localDate;
    private Set<Dish> dishSet;
    private Restaurant restaurant;


    public RestaurantMenu(LocalDate localDate, Set<Dish> dishSet, Restaurant restaurant) {
        this.localDate = localDate;
        this.dishSet = dishSet;
        this.restaurant = restaurant;
    }

    public RestaurantMenu() {
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Set<Dish> getDishSet() {
        return dishSet;
    }

    public void setDishSet(Set<Dish> dishSet) {
        this.dishSet = dishSet;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

}
