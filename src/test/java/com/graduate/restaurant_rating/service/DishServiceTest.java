package com.graduate.restaurant_rating.service;

import com.graduate.restaurant_rating.domain.Dish;
import com.graduate.restaurant_rating.testdata.DishData;
import com.graduate.restaurant_rating.to.DishWithVotes;
import com.graduate.restaurant_rating.util.exception.NotFoundException;
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
    private DishService service;
    private ArrayList<Dish> allDishes = new ArrayList<>(DishData.getAllDishes());
    private int CrPoId = CRUMB_POTATOSHKA.getId();

    @Test
    public void create() {
        Dish newDish = getCreated();
        service.create(newDish);
        allDishes.add(newDish);
        assertThat(service.getAll()).isEqualTo(allDishes);
    }

    @Test
    public void update() {
        Dish updated = getUpdated();
        service.update(updated, CrPoId);
        assertThat(service.get(CrPoId)).isEqualTo(updated);
    }

    @Test
    public void delete() {
        allDishes.remove(CRUMB_POTATOSHKA);
        service.delete(CrPoId);
        assertThat(allDishes).isEqualTo(service.getAll());
    }

    @Test
    public void get() {
        Dish actual = service.get(CrPoId);
        assertThat(actual).isEqualTo(CRUMB_POTATOSHKA);
    }

    @Test
    public void getAll() {
        List<Dish> actual = service.getAll();
        List<Dish> expected = DishData.getAllDishes();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void getAllWithVotes() {
        List<DishWithVotes> actual = service.getAllWithVotes();
        List<DishWithVotes> expected = DishTestUtils.getListWithVotes();
        assertThat(actual).isEqualTo(expected);

    }

    @Test
    public void getAllByDate() {
        LocalDate start = LocalDate.of(2018, 05, 30);
        LocalDate end = LocalDate.of(2018, 05, 31);
        List<Dish> actual = service.getAllByDate(start, end);
        ArrayList<Dish> expected = allDishes;
        expected.remove(LE_BIG_MAC);
        assertThat(actual).isEqualTo(expected);
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

}