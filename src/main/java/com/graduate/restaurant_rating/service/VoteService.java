package com.graduate.restaurant_rating.service;

import com.graduate.restaurant_rating.domain.Vote;

import java.time.LocalDateTime;
import java.util.List;


public interface VoteService {

    Vote create(Vote vote);

    Vote update(Vote vote, int userId);

    void delete(int id);

    List<Vote> getAll();

    Vote get(int id);

    List<Vote> getForDay(LocalDateTime localDate);

    Vote getForReVotingPeriodByUser(LocalDateTime localDate, int user_id);

    List<Vote> findAllByDateBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<Vote> findAllByDateBetweenAndDishId(LocalDateTime startDateTime, LocalDateTime endDateTime, Integer dishId);
}
