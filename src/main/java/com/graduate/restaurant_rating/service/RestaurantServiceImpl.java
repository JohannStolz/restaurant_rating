package com.graduate.restaurant_rating.service;

import com.graduate.restaurant_rating.domain.Restaurant;
import com.graduate.restaurant_rating.repos.ResturantRepo;
import com.graduate.restaurant_rating.to.VoteWinner;

import java.util.List;

import static com.graduate.restaurant_rating.util.ValidationUtil.checkForMatchId;

/**
 * Created by Johann Stolz 14.08.2018
 */
public class RestaurantServiceImpl implements RestaurantService {

    private ResturantRepo restaurantRepo;

    @Override
    public Restaurant create(Restaurant restaurant) {
        return restaurantRepo.save(restaurant);
    }

    @Override
    public Restaurant update(Restaurant restaurant, int restaurantId) {
        checkForMatchId(restaurant, restaurantId);
        return restaurantRepo.save(restaurant);
    }

    @Override
    public void delete(int id) {
        restaurantRepo.deleteById(id);
    }

    @Override
    public Restaurant get(int id) {

        return restaurantRepo.findById(id);
    }

    @Override
    public List<Restaurant> getAll() {

        return restaurantRepo.findAll();
    }

    @Override
    public Restaurant getTheBest(VoteWinner winner) {

        return winner.getRestaurant();
    }
}
