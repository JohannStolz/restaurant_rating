package com.graduate.restaurant_rating.service;

import com.graduate.restaurant_rating.domain.Dish;
import com.graduate.restaurant_rating.testdata.DishData;
import com.graduate.restaurant_rating.to.DishWithVotes;
import com.graduate.restaurant_rating.util.exception.NotFoundException;
import com.graduate.restaurant_rating.utils.DishTestUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static com.graduate.restaurant_rating.testdata.DishData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;


public class DishServiceTest extends AbstractServiceTest {
    @Autowired
    private DishService dishService;

    @Test
    public void create() {
        Dish newDish = getCreated();
        ArrayList<Dish> dishes = new ArrayList<>(DishData.getAllDishes());
        dishService.create(newDish);
        dishes.add(newDish);
        assertThat(dishService.getAll()).isEqualTo(dishes);
    }

    @Test
    public void update() {
        Dish updated = getUpdated();
        dishService.update(updated, CRUMB_POTATOSHKA.getId());
        assertThat(dishService.get(CRUMB_POTATOSHKA.getId())).isEqualTo(updated);
    }

    @Test
    public void delete() {
        ArrayList<Dish> allDishes = new ArrayList<>(DishData.getAllDishes());
        allDishes.remove(CRUMB_POTATOSHKA);
        dishService.delete(CRUMB_POTATOSHKA.getId());
        assertThat(allDishes).isEqualTo(dishService.getAll());
    }

    @Test
    public void get() {
        Dish actual = dishService.get(CRUMB_POTATOSHKA.getId());
        assertThat(actual).isEqualTo(CRUMB_POTATOSHKA);
    }

    @Test
    public void getAll() {
        List<Dish> actual = dishService.getAll();
        List<Dish> expected = DishData.getAllDishes();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void getAllWithVotes() {
    List<DishWithVotes> actual = dishService.getAllWithVotes();
    List<DishWithVotes> expected = DishTestUtils.getListWithVotes();
    assertThat(actual).isEqualTo(expected);

    }

    @Test
    public void getAllByDate() {
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        dishService.get(1);
    }
}