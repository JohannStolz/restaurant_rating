package com.graduate.restaurant_rating.service;

import com.graduate.restaurant_rating.domain.Dish;
import com.graduate.restaurant_rating.testdata.DishData;
import com.graduate.restaurant_rating.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.graduate.restaurant_rating.testdata.DishData.*;
import static com.graduate.restaurant_rating.utils.TestUtil.assertMatch;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DishServiceTest extends AbstractServiceTest {
    @Autowired
    private DishService service;
    private List<Dish> allDishes = new ArrayList<>(DishData.getAllDishes());
    private int CrPoId = CRUMB_POTATOSHKA.getId();

    @Test
    public void create() {
        Dish newDish = getCreated();
        service.create(newDish);
        allDishes.add(newDish);
        assertMatch(service.getAll(), allDishes);
    }

    @Test
    public void update() {
        Dish updated = getUpdated();
        service.update(updated, CrPoId);
        assertMatch(service.get(CrPoId), updated);
    }

    @Test
    public void delete() {
        allDishes.remove(CRUMB_POTATOSHKA);
        service.delete(CrPoId);
        assertMatch(allDishes, service.getAll());
    }

    @Test
    public void get() {
        Dish actual = service.get(CrPoId);
        assertMatch(actual, CRUMB_POTATOSHKA);
    }

    @Test
    public void getAll() {
        List<Dish> actual = service.getAll();
        List<Dish> expected = allDishes;
        assertMatch(actual, expected);
    }

    @Test
    public void getAllByDate() {
        List<Dish> actual = service.getAllByDate(LocalDateTime.now().toLocalDate(), LocalDateTime.now().toLocalDate());
        List<Dish> expected = allDishes;
        expected.remove(CRUMB_POTATOSHKA);
        assertMatch(actual, expected);
    }

    @Test
    public void getNotFound() throws Exception {
        int id = 1;
        assertThrows(NotFoundException.class, () -> service.get(id));
    }

    @Test
    public void updateNotFound() {
        int id = 0;
        Dish updated = getCreated();
        NotFoundException e = assertThrows(NotFoundException.class, () -> service.update(updated, id));
        assertEquals(e.getMessage(), "Not found entity with id=" + id);
    }

    @Test
    public void deleteNotFound() {
        Dish deleted = getCreated();
        deleted.setId(1);
        EmptyResultDataAccessException e = assertThrows(EmptyResultDataAccessException.class, () -> service.delete(deleted.getId()));
        assertEquals(e.getMessage(), "No " + Dish.class + " entity with id " + deleted.getId() + " exists!");
    }

}