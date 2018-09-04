package com.graduate.restaurant_rating.repos;

import com.graduate.restaurant_rating.domain.Restaurant;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Johann Stolz 15.08.2018
 */
public interface ResturantRepo extends PagingAndSortingRepository<Restaurant, Integer> {

    Restaurant findById(int id);

    Restaurant save(Restaurant restaurant);

    void deleteById(int id);

    List<Restaurant> findAll();

}
