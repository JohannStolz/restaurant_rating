package com.graduate.restaurant_rating.service;

import com.graduate.restaurant_rating.domain.Vote;
import com.graduate.restaurant_rating.repos.VoteRepo;
import com.graduate.restaurant_rating.util.exception.NotFoundException;
import com.graduate.restaurant_rating.util.exception.ReVoteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import static com.graduate.restaurant_rating.util.ValidationUtil.checkNotFoundWithId;


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
    public Vote update(Vote vote, int userId) {

        if (checkUserIdAndReVotePeriod(vote)) {
            throw new ReVoteException("The time for re-voting is up to 11 hours");
        }
        return checkNotFoundWithId(voteRepo.save(vote), vote.getId());
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
        return voteRepo.findById(id).orElseThrow(() -> new NotFoundException("Not found entity with id=" + id));
    }

    @Override
    public List<Vote> getForDay(LocalDateTime localDate) {
        List<LocalDateTime> list = getReVotingPeriodFromDate(localDate);
        return voteRepo.findAllByDateBetween(list.get(0), list.get(1));
    }


    @Override
    public Vote getForReVotingPeriodByUser(LocalDateTime localDate, int user_id) {
        List<LocalDateTime> list = getReVotingPeriodFromDate(localDate);
        return voteRepo.findAllByDateBetweenAndUserId(list.get(0), list.get(1), user_id);
    }

    @Override
    public List<Vote> findAllByDateBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return voteRepo.findAllByDateBetween(startDateTime, endDateTime);
    }

    @Override
    public List<Vote> findAllByDateBetweenAndDishId(LocalDateTime startDateTime, LocalDateTime endDateTime, Integer dishId) {
        return voteRepo.findAllByDateBetweenAndDishId(startDateTime, endDateTime, dishId);
    }

    private boolean checkUserIdAndReVotePeriod(Vote vote) {
        get(vote.getId());
        int userId = vote.getUserId();
        if (!vote.isNew() && get(vote.getId(), userId) == null) {
            return true;
        } else vote.setUserId(userId);
        LocalDateTime date = vote.getDate();
        Vote reVote = getForReVotingPeriodByUser(date, userId);
        if (reVote == null) {
            return false;
        } else if (vote.getDate().toLocalTime().isAfter(END_OF_VOTING)) {
            return (reVote.getDishId() == vote.getDishId() && reVote.getRestaurantId() == vote.getRestaurantId());
        } else return false;
    }

    private List<LocalDateTime> getReVotingPeriodFromDate(LocalDateTime endDate) {
        LocalDateTime startDate = endDate.truncatedTo(ChronoUnit.DAYS);
        if (endDate.toLocalTime().isAfter(END_OF_VOTING)) {
            endDate = startDate.plusHours(ELEVEN_O_CLOCK);
        }

        return Arrays.asList(startDate, endDate);
    }

    public Vote get(int id, int userId) {
        return voteRepo.findById(id).filter(vote -> vote.getUserId() == userId).orElse(null);
    }

}
