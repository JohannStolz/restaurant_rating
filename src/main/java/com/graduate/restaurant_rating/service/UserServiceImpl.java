package com.graduate.restaurant_rating.service;

import com.graduate.restaurant_rating.domain.User;
import com.graduate.restaurant_rating.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.graduate.restaurant_rating.util.ValidationUtil.checkForMatchId;

/**
 * Created by Johann Stolz 14.08.2018
 */
@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    @Transactional
    @Override
    public User create(User user) {
        return userRepo.save(user);
    }
    @Transactional
    @Override
    public User update(User user, int userId) {
        checkForMatchId(user, userId);
        return userRepo.save(user);
    }
    @Transactional
    @Override
    public void delete(int id) {
        userRepo.deleteById(id);
    }

    @Override
    public User get(int id) {
        return userRepo.findById(id);
    }

    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }
}
