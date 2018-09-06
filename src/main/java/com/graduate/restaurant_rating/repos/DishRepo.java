package com.graduate.restaurant_rating.repos;

import com.graduate.restaurant_rating.domain.Dish;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Johann Stolz 15.08.2018
 */
public interface DishRepo extends PagingAndSortingRepository<Dish, Integer> {

    Dish findById(int id);

    Dish save(Dish dish);

    void deleteById(int id);

    List<Dish> findAll();

    List<Dish> findAllByDate(LocalDate date);

    List<Dish> findAllByDateBetween(LocalDate startDate, LocalDate endDate);
}
