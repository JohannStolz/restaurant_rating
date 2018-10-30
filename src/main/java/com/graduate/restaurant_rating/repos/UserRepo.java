package com.graduate.restaurant_rating.repos;

import com.graduate.restaurant_rating.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface UserRepo extends PagingAndSortingRepository<User, Integer> {

    User save(User user);

    void deleteById(int id);

    User findByName(String name);

    List<User> findAll();

}
