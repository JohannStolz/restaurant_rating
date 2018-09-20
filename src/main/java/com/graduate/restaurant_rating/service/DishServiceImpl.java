package com.graduate.restaurant_rating.service;

import com.graduate.restaurant_rating.domain.Dish;
import com.graduate.restaurant_rating.domain.Vote;
import com.graduate.restaurant_rating.repos.DishRepo;
import com.graduate.restaurant_rating.repos.VoteRepo;
import com.graduate.restaurant_rating.to.DishWithVotes;
import com.graduate.restaurant_rating.util.DishUtil;
import com.graduate.restaurant_rating.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.graduate.restaurant_rating.util.ValidationUtil.checkForMatchId;
import static com.graduate.restaurant_rating.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Johann Stolz 14.08.2018
 */
@Transactional(readOnly = true)
@Service
public class DishServiceImpl implements DishService {
    private final DishRepo dishRepo;
    private final VoteRepo voteRepo;

    @Autowired
    public DishServiceImpl(DishRepo dishRepo, VoteRepo voteRepo) {
        this.dishRepo = dishRepo;
        this.voteRepo = voteRepo;
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
    public List<DishWithVotes> getAllWithVotes() {
        List<Dish> dishes = getAll();
        List<Vote> votes = voteRepo.findAll();
        return DishUtil.getWithVotes(dishes, votes);
    }

    @Override
    public List<Dish> getAllByDate(LocalDate startDate, LocalDate ensDate) {
        return dishRepo.findAllByDateBetween(startDate, ensDate);
    }


}
