package com.graduate.restaurant_rating.service;

import com.graduate.restaurant_rating.domain.Restaurant;
import com.graduate.restaurant_rating.to.RestaurantWithVotes;

import java.util.List;

/**
 * Created by Johann Stolz 14.08.2018
 */
public interface RestaurantService {

    Restaurant create(Restaurant restaurant);

    Restaurant update(Restaurant restaurant, int restaurantId);

    void delete(int id);

    Restaurant get(int id);

    List<Restaurant> getAll();

    List<RestaurantWithVotes> getAllWithVotes();
}
