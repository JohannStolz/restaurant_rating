package com.graduate.restaurant_rating.service;

import com.graduate.restaurant_rating.domain.Dish;
import com.graduate.restaurant_rating.repos.DishRepo;
import com.graduate.restaurant_rating.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.graduate.restaurant_rating.util.ValidationUtil.checkForMatchId;
import static com.graduate.restaurant_rating.util.ValidationUtil.checkNotFoundWithId;


@Transactional(readOnly = true)
@Service
public class DishServiceImpl implements DishService {
    private final DishRepo dishRepo;

    @Autowired
    public DishServiceImpl(DishRepo dishRepo) {
        this.dishRepo = dishRepo;
    }

    @Transactional
    @Override
    public Dish create(Dish dish) {
        return dishRepo.save(dish);
    }

    @Transactional
    @Override
    public Dish update(Dish dish, int dishId) throws NotFoundException {
        checkForMatchId(dish, dishId);
        return checkNotFoundWithId(dishRepo.save(dish), dishId);
    }

    @Transactional
    @Override
    public void delete(int id) throws NotFoundException {
        dishRepo.deleteById(id);

    }

    @Override
    public Dish get(int id) throws NotFoundException {
        return checkNotFoundWithId(dishRepo.findById(id), id);
    }

    @Override
    public List<Dish> getAll() {
        return dishRepo.findAll();
    }

    @Override
    public List<Dish> getAllByDate(LocalDate startDate, LocalDate ensDate) {
        return dishRepo.findAllByDateBetween(startDate, ensDate);
    }


}
