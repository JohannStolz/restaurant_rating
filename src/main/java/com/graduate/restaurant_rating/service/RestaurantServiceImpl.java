package com.graduate.restaurant_rating.service;

import com.graduate.restaurant_rating.domain.Restaurant;
import com.graduate.restaurant_rating.domain.Vote;
import com.graduate.restaurant_rating.repos.ResturantRepo;
import com.graduate.restaurant_rating.repos.VoteRepo;
import com.graduate.restaurant_rating.to.RestaurantWithVotes;
import com.graduate.restaurant_rating.to.VoteWinner;
import com.graduate.restaurant_rating.util.RestaurantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.graduate.restaurant_rating.util.ValidationUtil.checkForMatchId;
import static com.graduate.restaurant_rating.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Johann Stolz 14.08.2018
 */
@Transactional(readOnly = true)
@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final ResturantRepo restaurantRepo;
    private final VoteRepo voteRepo;

    @Autowired
    public RestaurantServiceImpl(ResturantRepo restaurantRepo, VoteRepo voteRepo) {
        this.restaurantRepo = restaurantRepo;
        this.voteRepo = voteRepo;
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
        return checkNotFoundWithId(restaurantRepo.save(restaurant), restaurantId);
    }

    @Transactional
    @Override
    public void delete(int id) {
        restaurantRepo.deleteById(id);
    }

    @Override
    public Restaurant get(int id) {
        return checkNotFoundWithId(restaurantRepo.findById(id), id);
    }

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepo.findAll();
    }

    @Override
    public Restaurant getTheBest(VoteWinner winner) {

        return winner.getRestaurant();
    }

    @Override
    public List<RestaurantWithVotes> getAllWithVotes() {
        List<Restaurant> restaurants = getAll();
        List<Vote> votes = voteRepo.findAll();
        return RestaurantUtil.getWithVotes(restaurants, votes);
    }
}
