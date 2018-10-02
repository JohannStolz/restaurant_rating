package com.graduate.restaurant_rating.testdata;

import com.graduate.restaurant_rating.domain.Vote;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.graduate.restaurant_rating.domain.AbstractBaseEntity.SEQ_START;
import static com.graduate.restaurant_rating.testdata.DishData.*;
import static com.graduate.restaurant_rating.testdata.RestaurantData.*;
import static com.graduate.restaurant_rating.testdata.UserData.*;

/**
 * Created by Johann Stolz 05.09.2018
 */
public class VoteData {
    public static final int ADMIN_VOTE_ID = SEQ_START + 9;
    public static final int USER1_VOTE_ID = SEQ_START + 10;
    public static final int USER2_VOTE_ID = SEQ_START + 11;
    public static final int USER2_SECOND_VOTE_ID = SEQ_START + 12;
    public static final int USER1_SECOND_VOTE_ID = SEQ_START + 13;
    public static final LocalDateTime BEFORE_ELEVEN = LocalDateTime.now().withHour(10).withMinute(59).withSecond(59);
    public static final LocalDateTime AFTER_ELEVEN = LocalDateTime.now().withHour(11).withMinute(0).withSecond(1);
    public static final LocalDateTime AFTER_MIDNIGHT_TODAY = LocalDateTime.now().withHour(0).withMinute(0).withSecond(1);
    public static final LocalTime END_OF_VOTING = LocalTime.of(11, 0);

    public static final Vote ADMIN_VOTE = new Vote(ADMIN_VOTE_ID, ADMIN, CRUMB_POTATOSHKA, CRUMB_POTATO, AFTER_MIDNIGHT_TODAY); //today
    public static final Vote USER1_VOTE = new Vote(USER1_VOTE_ID, USER1, BELYASH_FOR_GENTS, BELYASH, BEFORE_ELEVEN); //today
    public static final Vote USER2_VOTE = new Vote(USER2_VOTE_ID, USER2, LE_BIG_MAC, MAXIM, AFTER_ELEVEN); //today after 11
    public static final Vote USER2_SECOND_VOTE_BEFORE_ELEVEN = new Vote(USER2_SECOND_VOTE_ID, USER2, BELYASH_FOR_GENTS, BELYASH, AFTER_MIDNIGHT_TODAY);//today
    public static final Vote USER1_SECOND_VOTE = new Vote(USER1_SECOND_VOTE_ID, USER1, BELYASH_FOR_GENTS, BELYASH, AFTER_MIDNIGHT_TODAY.minusMinutes(5)); //yesterday


    public static Vote getCreated() {
        return new Vote(null, ADMIN, LE_BIG_MAC, MAXIM, LocalDateTime.now());
    }

    public static Vote getUpdated() {
        return new Vote(USER1_VOTE_ID, USER1, CRUMB_POTATOSHKA, CRUMB_POTATO, LocalDateTime.now());
    }

    public static Vote getUpdatedUser2() {
        if (LocalDateTime.now().toLocalTime().isBefore(END_OF_VOTING)) {
            return new Vote(USER2_SECOND_VOTE_ID, USER2, BELYASH_FOR_GENTS, BELYASH, LocalDateTime.now()); // before
        }
        return new Vote(USER2_VOTE_ID, USER2, BELYASH_FOR_GENTS, BELYASH, LocalDateTime.now());  //after
    }

    public static List<Vote> getAllVotes() {
        return Arrays.asList(ADMIN_VOTE, USER1_VOTE, USER2_VOTE, USER2_SECOND_VOTE_BEFORE_ELEVEN, USER1_SECOND_VOTE);
    }

    public static List<Vote> getForToday() {
        if (LocalDateTime.now().toLocalTime().isBefore(END_OF_VOTING)) {
            List<Vote> list = Arrays.asList(ADMIN_VOTE, USER1_VOTE, USER2_SECOND_VOTE_BEFORE_ELEVEN);

            return list.stream()
                    .filter(vote -> vote.getDate().isBefore(LocalDateTime.now()))
                    .collect(Collectors.toList());
        }
        return Collections.singletonList(USER2_VOTE);
    }


    @SafeVarargs
    public static void getVotesWithTruncatedLocaleDateTime(List<Vote>... lists) {
        for (List<Vote> list : lists) {
            list.forEach(vote -> vote.setDate(vote.getDate().truncatedTo(ChronoUnit.SECONDS)));
        }

    }

    public static void getVoteWithTruncatedLocaleDateTime(Vote... votes) {
        for (Vote vote : votes) {
            vote.setDate(vote.getDate().truncatedTo(ChronoUnit.SECONDS));
        }
    }
}
