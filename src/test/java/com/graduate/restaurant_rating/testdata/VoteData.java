package com.graduate.restaurant_rating.testdata;

import com.graduate.restaurant_rating.domain.Vote;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.graduate.restaurant_rating.testdata.DishData.*;
import static com.graduate.restaurant_rating.testdata.RestaurantData.*;
import static com.graduate.restaurant_rating.testdata.UserData.*;

/**
 * Created by Johann Stolz 05.09.2018
 */
public class VoteData {
    public static final Vote ADMIN_VOTE = new Vote(ADMIN, CRUMB_POTATOSHKA, CRUMB_POTATO, LocalDate.now());
    public static final Vote USER1_VOTE = new Vote(USER1, BELYASH_FOR_GENTS, BELYASH, LocalDate.now());
    public static final Vote USER2_VOTE = new Vote(USER2, LE_BIG_MAC, MAXIM, LocalDate.now());
    public static final Vote NEW_VOTE = new Vote(null, ADMIN, LE_BIG_MAC, MAXIM, LocalDate.now());

    public static List<Vote> getAllVotes() {
        return Arrays.asList(ADMIN_VOTE, USER1_VOTE, USER2_VOTE, NEW_VOTE);
    }
}
