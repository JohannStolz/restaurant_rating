package com.graduate.restaurant_rating.service;

import com.graduate.restaurant_rating.domain.User;

import java.util.List;

/**
 * Created by Johann Stolz 14.08.2018
 */
public interface UserService {

    User create(User user);

    User update(User user, int userId);

    void delete(int id);

    User get(int id);

    List<User> getAll();

    void enable(int id, boolean enabled);

}
