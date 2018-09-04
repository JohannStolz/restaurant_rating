package com.graduate.restaurant_rating.service;

import com.graduate.restaurant_rating.domain.Vote;
import com.graduate.restaurant_rating.repos.VoteRepo;

import java.time.LocalDate;
import java.util.List;

import static com.graduate.restaurant_rating.util.ValidationUtil.checkForMatchId;

/**
 * Created by Johann Stolz 14.08.2018
 */
public class VoteServiceImpl implements VoteService {

    private VoteRepo voteRepo;

    @Override
    public Vote create(Vote vote) {
        return voteRepo.save(vote);
    }

    @Override
    public Vote update(Vote vote, int voteId) {
        checkForMatchId(vote, voteId);
        return voteRepo.save(vote);
    }

    @Override
    public void delete(int id) {
        voteRepo.deleteById(id);
    }

    @Override
    public List<Vote> getAll() {
        return voteRepo.findAll();
    }

    @Override
    public List<Vote> getForDay(LocalDate localDate) {
        return voteRepo.findAllByDate(localDate);
    }
}
