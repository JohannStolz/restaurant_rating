package com.graduate.restaurant_rating.repos;

import com.graduate.restaurant_rating.domain.Restaurant;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface RestaurantRepo extends PagingAndSortingRepository<Restaurant, Integer> {

    Restaurant save(Restaurant restaurant);

    void deleteById(int id);

    List<Restaurant> findAll();

}
