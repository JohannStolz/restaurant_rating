package com.graduate.restaurant_rating.service;

import com.graduate.restaurant_rating.domain.Dish;
import com.graduate.restaurant_rating.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Johann Stolz 14.08.2018
 */
public class DishServiceImpl implements DishService {

    
    @Override
    public Dish create(Dish dish) {
        return null;
    }

    @Override
    public Dish update(Dish dish) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(int id) throws NotFoundException {

    }

    @Override
    public Dish get(int id) throws NotFoundException {
        return null;
    }

    @Override
    public List<Dish> getAll() {
        return null;
    }

    @Override
    public List<Dish> getAllByDate(LocalDate date) {
        return null;
    }
}
