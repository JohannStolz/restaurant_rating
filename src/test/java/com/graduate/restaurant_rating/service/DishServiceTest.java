package com.graduate.restaurant_rating.service;

import com.graduate.restaurant_rating.domain.Dish;
import com.graduate.restaurant_rating.testdata.DishData;
import com.graduate.restaurant_rating.to.DishWithVotes;
import com.graduate.restaurant_rating.util.exception.NotFoundException;
import com.graduate.restaurant_rating.util.exception.WrongIdException;
import com.graduate.restaurant_rating.utils.DishTestUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.graduate.restaurant_rating.testdata.DishData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DishServiceTest extends AbstractServiceTest {
    @Autowired
    private DishService dishService;

    private int CrPoId = CRUMB_POTATOSHKA.getId();

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
        dishService.update(updated, CrPoId);
        assertThat(dishService.get(CrPoId)).isEqualTo(updated);
    }

    @Test
    public void delete() {
        ArrayList<Dish> allDishes = new ArrayList<>(DishData.getAllDishes());
        allDishes.remove(CRUMB_POTATOSHKA);
        dishService.delete(CrPoId);
        assertThat(allDishes).isEqualTo(dishService.getAll());
    }

    @Test
    public void get() {
        Dish actual = dishService.get(CrPoId);
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
        LocalDate start = LocalDate.of(2018, 05, 30);
        LocalDate end = LocalDate.of(2018, 05, 31);
        List<Dish> actual = dishService.getAllByDate(start, end);
        ArrayList<Dish> expected = new ArrayList<>(DishData.getAllDishes());
        expected.remove(LE_BIG_MAC);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        dishService.get(1);
    }

    @Test
    public void updateNotFound() {
        int id = 0;
        Dish updated = getCreated();
        WrongIdException e = assertThrows(WrongIdException.class, () -> dishService.update(updated, id));
        assertEquals(e.getMessage(), "Dish.id not equals id: " + id);
    }

    @Test
    public void updateWrongId() {
        int id = 0;
        WrongIdException e = assertThrows(WrongIdException.class, () -> dishService.update(BELYASH_FOR_GENTS, id));
        assertEquals(e.getMessage(), "Dish.id not equals id: " + id);
    }
}