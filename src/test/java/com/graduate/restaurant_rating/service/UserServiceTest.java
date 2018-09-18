package com.graduate.restaurant_rating.service;

import com.graduate.restaurant_rating.domain.User;
import com.graduate.restaurant_rating.testdata.UserData;
import com.graduate.restaurant_rating.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.List;

import static com.graduate.restaurant_rating.domain.Role.ROLE_USER;
import static com.graduate.restaurant_rating.domain.Sex.SEX_WHATEVER;
import static com.graduate.restaurant_rating.testdata.UserData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


public class UserServiceTest extends AbstractServiceTest {

    private ArrayList<User> allUsers = new ArrayList<>(UserData.getAllUsers());
    @Autowired
    private UserService userService;

    @Test
    public void create() {
        User newUser = getCreated();
        userService.create(newUser);
        allUsers.add(newUser);
        assertThat(userService.getAll()).isEqualTo(allUsers);
    }

    @Test
    public void update() {
        User updated = getUpdated();
        userService.update(updated, USER1_ID);
        assertThat(userService.get(USER1_ID)).isEqualTo(updated);
    }

    @Test
    public void delete() {
        allUsers.remove(USER1);
        userService.delete(USER1_ID);
        assertThat(allUsers).isEqualTo(userService.getAll());
    }

    @Test
    public void get() {
        User actual = userService.get(USER1_ID);
        assertThat(actual).isEqualTo(USER1);
    }

    @Test
    public void getAll() {
        List<User> actual = allUsers;
        List<User> expected = UserData.getAllUsers();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void duplicateMailCreate() throws Exception {
        assertThrows(DataAccessException.class, () ->
                userService.create(new User(null, "Duplicate", 112, SEX_WHATEVER, "user1@rating.com", true, "pass", ROLE_USER)));
    }

    @Test
    public void getNotFound() throws Exception {
        int id = 1;
        assertThrows(NotFoundException.class, () ->
                userService.get(id));
    }

    @Test
    public void updateNotFound() {
        int id = 0;
        User updated = getCreated();
        NotFoundException e = assertThrows(NotFoundException.class, () -> userService.update(updated, id));
        assertEquals(e.getMessage(), "Not found entity with id=" + id);
    }

    @Test
    public void enable() {
        userService.enable(USER1_ID, false);
        assertFalse(userService.get(USER1_ID).isEnabled());
        userService.enable(USER1_ID, true);
        assertTrue(userService.get(USER1_ID).isEnabled());
    }

}
