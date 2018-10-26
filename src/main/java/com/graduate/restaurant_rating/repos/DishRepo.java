package com.graduate.restaurant_rating.repos;

import com.graduate.restaurant_rating.domain.Dish;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;


public interface DishRepo extends PagingAndSortingRepository<Dish, Integer> {

    Dish findById(int id);

    Dish save(Dish dish);

    void deleteById(int id);

    List<Dish> findAll();

    List<Dish> findAllByDateBetween(LocalDate startDate, LocalDate endDate);
}
