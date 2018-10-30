package com.graduate.restaurant_rating.service;

import com.graduate.restaurant_rating.domain.Restaurant;

import java.util.List;


public interface RestaurantService {

    Restaurant create(Restaurant restaurant);

    Restaurant update(Restaurant restaurant, int restaurantId);

    void delete(int id);

    Restaurant get(int id);

    List<Restaurant> getAll();

}
