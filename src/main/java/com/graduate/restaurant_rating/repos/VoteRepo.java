package com.graduate.restaurant_rating.repos;

import com.graduate.restaurant_rating.domain.Vote;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Johann Stolz 15.08.2018
 */
public interface VoteRepo extends PagingAndSortingRepository<Vote, Integer> {

    Vote save(Vote vote);

    void deleteById(int id);

    Vote findById(int id);

    List<Vote> findAll();

    List<Vote> findAllByDate(LocalDateTime date);
}
