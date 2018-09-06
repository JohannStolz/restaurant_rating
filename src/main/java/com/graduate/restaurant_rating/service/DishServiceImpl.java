package com.graduate.restaurant_rating.service;

import com.graduate.restaurant_rating.domain.Dish;
import com.graduate.restaurant_rating.repos.DishRepo;
import com.graduate.restaurant_rating.to.DishWithVotes;
import com.graduate.restaurant_rating.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.graduate.restaurant_rating.util.ValidationUtil.checkForMatchId;

/**
 * Created by Johann Stolz 14.08.2018
 */
@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishRepo dishRepo;

    @Override
    public Dish create(Dish dish) {
        return dishRepo.save(dish);
    }

    @Override
    public Dish update(Dish dish, int dishId) throws NotFoundException {
        checkForMatchId(dish, dishId);
        return dishRepo.save(dish);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        dishRepo.deleteById(id);
    }

    @Override
    public Dish get(int id) throws NotFoundException {
        return dishRepo.findById(id);
    }

    @Override
    public List<Dish> getAll() {
        return dishRepo.findAll();
    }

    @Override
    public List<DishWithVotes> getAllWithVotes() {
        List<Dish> dishes = getAll();
        for (Dish d : dishes) {

        }
        return null; // STUB!!!!!!
    }

    @Override
    public List<Dish> getAllByDate(LocalDate startDate, LocalDate ensDate) {
        return dishRepo.findAllByDateBetween(startDate, ensDate);
    }


}
