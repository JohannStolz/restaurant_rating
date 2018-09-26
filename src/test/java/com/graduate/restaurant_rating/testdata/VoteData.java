package com.graduate.restaurant_rating.testdata;

import com.graduate.restaurant_rating.domain.Vote;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

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


    public static final Vote ADMIN_VOTE = new Vote(ADMIN_VOTE_ID, ADMIN, CRUMB_POTATOSHKA, CRUMB_POTATO, LocalDateTime.now().minusDays(1));
    public static final Vote USER1_VOTE = new Vote(USER1_VOTE_ID, USER1, BELYASH_FOR_GENTS, BELYASH, LocalDateTime.now());
    public static final Vote USER2_VOTE = new Vote(USER2_VOTE_ID, USER2, LE_BIG_MAC, MAXIM, LocalDateTime.now());

    public static Vote getCreated() {
        return new Vote(null, ADMIN, LE_BIG_MAC, MAXIM, LocalDateTime.now());
    }

    public static Vote getUpdated() {
        return new Vote(USER1_VOTE_ID, USER1, BELYASH_FOR_GENTS, BELYASH, LocalDateTime.now().plusHours(5));
    }

    public static List<Vote> getAllVotes() {
        return Arrays.asList(ADMIN_VOTE, USER1_VOTE, USER2_VOTE);
    }

    public static List<Vote> getForToday() {
        return Arrays.asList(USER1_VOTE, USER2_VOTE);
    }
    @SafeVarargs
    public final static void getVotesWithTruncatedLocaleDateTime(List<Vote>... lists) {
        for (List<Vote> list : lists) {
            list.forEach(vote -> vote.setDate(vote.getDate().truncatedTo(ChronoUnit.HOURS)));
        }

    }

    public static void getVoteWithTruncatedLocaleDateTime(Vote... votes) {
        for (Vote vote : votes) {
            vote.setDate(vote.getDate().truncatedTo(ChronoUnit.HOURS));
        }
    }
}
