package com.graduate.restaurant_rating.service;

import com.graduate.restaurant_rating.domain.Restaurant;
import com.graduate.restaurant_rating.repos.ResturantRepo;
import com.graduate.restaurant_rating.to.VoteWinner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.graduate.restaurant_rating.util.ValidationUtil.checkForMatchId;

/**
 * Created by Johann Stolz 14.08.2018
 */
@Transactional(readOnly = true)
@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final ResturantRepo restaurantRepo;

    @Autowired
    public RestaurantServiceImpl(ResturantRepo restaurantRepo) {
        this.restaurantRepo = restaurantRepo;
    }
    @Transactional
    @Override
    public Restaurant create(Restaurant restaurant) {
        return restaurantRepo.save(restaurant);
    }
    @Transactional
    @Override
    public Restaurant update(Restaurant restaurant, int restaurantId) {
        checkForMatchId(restaurant, restaurantId);
        return restaurantRepo.save(restaurant);
    }
    @Transactional
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
