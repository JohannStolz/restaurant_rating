package com.graduate.restaurant_rating.service;

import com.graduate.restaurant_rating.domain.User;
import com.graduate.restaurant_rating.testdata.UserData;
import com.graduate.restaurant_rating.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;

import static com.graduate.restaurant_rating.domain.Role.ROLE_USER;
import static com.graduate.restaurant_rating.domain.Sex.SEX_WHATEVER;
import static com.graduate.restaurant_rating.testdata.UserData.*;
import static org.junit.jupiter.api.Assertions.*;


public class UserServiceTest extends AbstractServiceTest {

    private List<User> allUsers = new ArrayList<>(UserData.getAllUsers());
    @Autowired
    private UserService service;

    @Test
    public void create() {
        User newUser = getCreated();
        service.create(newUser);
        allUsers.add(newUser);
        assertMatchUsers(service.getAll(), allUsers);
    }

    @Test
    public void update() {
        User updated = getUpdated();
        service.update(updated, USER1_ID);
        assertMatchUser(service.get(USER1_ID), updated);
    }

    @Test
    public void delete() {
        allUsers.remove(USER1);
        service.delete(USER1_ID);
        assertMatchUsers(allUsers, service.getAll());
    }

    @Test
    public void get() {
        User actual = service.get(USER1_ID);
        assertMatchUser(actual, USER1);
    }

    @Test
    public void getAll() {
        List<User> actual = service.getAll();
        List<User> expected = allUsers;
        assertMatchUsers(actual, expected);
    }

    @Test
    public void duplicateMailCreate() throws Exception {
        assertThrows(DataAccessException.class, () ->
                service.create(new User(null, "Duplicate", 112, SEX_WHATEVER, "user1@rating.com", true, "pass", ROLE_USER)));
    }

    @Test
    public void getNotFound() throws Exception {
        int id = 1;
        assertThrows(NotFoundException.class, () ->
                service.get(id));
    }

    @Test
    public void updateNotFound() {
        int id = 0;
        User updated = getCreated();
        NotFoundException e = assertThrows(NotFoundException.class, () -> service.update(updated, id));
        assertEquals(e.getMessage(), "Not found entity with id=" + id);
    }

    @Test
    public void enable() {
        service.enable(USER1_ID, false);
        assertFalse(service.get(USER1_ID).isEnabled());
        service.enable(USER1_ID, true);
        assertTrue(service.get(USER1_ID).isEnabled());
    }

    @Test
    public void deleteNotFound() {
        User deleted = getCreated();
        deleted.setId(1);
        EmptyResultDataAccessException e = assertThrows(EmptyResultDataAccessException.class, () -> service.delete(deleted.getId()));
        assertEquals(e.getMessage(), "No " + User.class + " entity with id " + deleted.getId() + " exists!");
    }

}
