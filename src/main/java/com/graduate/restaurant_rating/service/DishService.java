package com.graduate.restaurant_rating.service;

import com.graduate.restaurant_rating.domain.Dish;
import com.graduate.restaurant_rating.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Johann Stolz 14.08.2018
 */
public interface DishService {

    Dish create(Dish dish);

    Dish update(Dish dish) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    Dish get(int id) throws NotFoundException;

    List<Dish> getAll();

    List<Dish> getAllByDate(LocalDate date);

}
