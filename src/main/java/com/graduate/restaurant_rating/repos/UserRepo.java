package com.graduate.restaurant_rating.repos;

import com.graduate.restaurant_rating.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Johann Stolz 15.08.2018
 */
public interface UserRepo extends PagingAndSortingRepository<User, Integer> {

    User save(User user);

    void deleteById(int id);

    User findById(int id);

    List<User> findAll();

}
