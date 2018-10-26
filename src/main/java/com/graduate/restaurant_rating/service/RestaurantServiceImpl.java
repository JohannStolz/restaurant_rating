package com.graduate.restaurant_rating.service;

import com.graduate.restaurant_rating.domain.Restaurant;
import com.graduate.restaurant_rating.domain.Vote;
import com.graduate.restaurant_rating.repos.RestaurantRepo;
import com.graduate.restaurant_rating.repos.VoteRepo;
import com.graduate.restaurant_rating.to.RestaurantWithVotes;
import com.graduate.restaurant_rating.util.RestaurantUtil;
import com.graduate.restaurant_rating.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.graduate.restaurant_rating.util.ValidationUtil.checkForMatchId;
import static com.graduate.restaurant_rating.util.ValidationUtil.checkNotFoundWithId;


@Transactional(readOnly = true)
@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepo restaurantRepo;
    private final VoteRepo voteRepo;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepo restaurantRepo, VoteRepo voteRepo) {
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
        return restaurantRepo.findById(id).orElseThrow(() -> new NotFoundException("Not found entity with id=" + id));
    }

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepo.findAll();
    }

    @Override
    public List<RestaurantWithVotes> getAllWithVotes() {
        List<Restaurant> restaurants = getAll();
        List<Integer> restaurantsId = restaurants.stream().map(Restaurant::getId).collect(Collectors.toList());
        List<Vote> votes = voteRepo.findAll();
        return RestaurantUtil.getWithVotes(restaurantsId, votes);
    }
}
