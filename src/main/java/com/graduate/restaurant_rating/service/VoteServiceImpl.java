package com.graduate.restaurant_rating.service;

import com.graduate.restaurant_rating.domain.User;
import com.graduate.restaurant_rating.domain.Vote;
import com.graduate.restaurant_rating.repos.VoteRepo;
import com.graduate.restaurant_rating.util.exception.ReVoteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import static com.graduate.restaurant_rating.util.ValidationUtil.checkForMatchId;
import static com.graduate.restaurant_rating.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Johann Stolz 14.08.2018
 */
@Transactional(readOnly = true)
@Service
public class VoteServiceImpl implements VoteService {
    private final int ELEVEN_O_CLOCK = 11;
    private final LocalTime END_OF_VOTING = LocalTime.of(ELEVEN_O_CLOCK, 0);
    private final VoteRepo voteRepo;

    @Autowired
    public VoteServiceImpl(VoteRepo voteRepo) {
        this.voteRepo = voteRepo;
    }

    @Transactional
    @Override
    public Vote create(Vote vote) {
        return voteRepo.save(vote);
    }

    @Transactional
    @Override
    public Vote update(Vote vote, int voteId) {
        checkForMatchId(vote, voteId);

        if (checkUserVoteInVotingPeriod(vote)) {
            throw new ReVoteException("The time for re-voting is up to 11 hours");
        }
        return checkNotFoundWithId(voteRepo.save(vote), voteId);
    }

    @Transactional
    @Override
    public void delete(int id) {
        voteRepo.deleteById(id);
    }

    @Override
    public List<Vote> getAll() {
        return voteRepo.findAll();
    }

    @Override
    public Vote get(int id) {
        return checkNotFoundWithId(voteRepo.findById(id), id);
    }


    @Override
    public List<Vote> getForDay(LocalDateTime localDate) {
        List<LocalDateTime> list = getVotingPeriodFromDate(localDate);
        return voteRepo.findAllByDateBetween(list.get(0), list.get(1));
    }

    @Override
    public Vote getForDayByUser(LocalDateTime localDate, User user) {
        List<LocalDateTime> list = getVotingPeriodFromDate(localDate);
        return voteRepo.findAllByDateBetweenAndUser(list.get(0), list.get(1), user);
    }

    private boolean checkUserVoteInVotingPeriod(Vote vote) {
        User user = vote.getUser();
        LocalDateTime date = vote.getDate();
        Vote reVote = getForDayByUser(date, user);
        return reVote != null;
    }

    private List<LocalDateTime> getVotingPeriodFromDate(LocalDateTime localDate) {
        LocalDateTime startDate = localDate.truncatedTo(ChronoUnit.DAYS);
        if (localDate.toLocalTime().isAfter(END_OF_VOTING)) {
            startDate = startDate.plusHours(ELEVEN_O_CLOCK);
        }
        return Arrays.asList(startDate, localDate);
    }
}
