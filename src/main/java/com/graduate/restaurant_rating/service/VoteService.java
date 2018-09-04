package com.graduate.restaurant_rating.service;

import com.graduate.restaurant_rating.domain.Vote;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Johann Stolz 14.08.2018
 */
public interface VoteService {

    Vote create(Vote vote);

    Vote update(Vote vote, int voteId);

    void delete(int id);

    List<Vote> getAll();

    List<Vote> getForDay(LocalDate localDate);
}
