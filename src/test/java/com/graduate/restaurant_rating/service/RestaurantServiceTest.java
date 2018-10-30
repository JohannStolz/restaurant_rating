package com.graduate.restaurant_rating.service;

import com.graduate.restaurant_rating.domain.Restaurant;
import com.graduate.restaurant_rating.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;

import static com.graduate.restaurant_rating.testdata.RestaurantData.*;
import static com.graduate.restaurant_rating.utils.TestUtil.assertMatch;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RestaurantServiceTest extends AbstractServiceTest {

    private List<Restaurant> allRestaurants = new ArrayList<>(getAllRestaurants());

    @Autowired
    private RestaurantService service;

    @Test
    public void create() {
        Restaurant newRestaurant = getCreated();
        service.create(newRestaurant);
        allRestaurants.add(newRestaurant);
        assertMatch(service.getAll(), allRestaurants);
    }

    @Test
    public void update() {
        Restaurant updated = getUpdated();
        service.update(updated, MAXIM_ID);
        assertMatch(service.get(MAXIM_ID), updated);
    }

    @Test
    public void delete() {
        allRestaurants.remove(BELYASH);
        service.delete(BELYASH_ID);
        assertMatch(allRestaurants, service.getAll());
    }

    @Test
    public void get() {
        Restaurant actual = service.get(BELYASH_ID);
        assertMatch(actual, BELYASH);
    }

    @Test
    public void getAll() {
        List<Restaurant> actual = service.getAll();
        List<Restaurant> expected = allRestaurants;
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
        Restaurant updated = getCreated();
        NotFoundException e = assertThrows(NotFoundException.class, () -> service.update(updated, id));
        assertEquals(e.getMessage(), "Not found entity with id=" + id);
    }

    @Test
    public void deleteNotFound() {
        Restaurant deleted = getCreated();
        deleted.setId(1);
        EmptyResultDataAccessException e = assertThrows(EmptyResultDataAccessException.class, () -> service.delete(deleted.getId()));
        assertEquals(e.getMessage(), "No " + Restaurant.class + " entity with id " + deleted.getId() + " exists!");
    }


}
