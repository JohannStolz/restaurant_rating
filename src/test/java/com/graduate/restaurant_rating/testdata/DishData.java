package com.graduate.restaurant_rating.testdata;

import com.graduate.restaurant_rating.domain.Dish;
import com.graduate.restaurant_rating.domain.Restaurant;
import com.graduate.restaurant_rating.domain.Vote;
import com.graduate.restaurant_rating.to.DishWithVotes;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.graduate.restaurant_rating.domain.AbstractBaseEntity.SEQ_START;
import static com.graduate.restaurant_rating.testdata.RestaurantData.*;


public class DishData {
    public static final int CP_ID = SEQ_START + 6;
    public static final int BFG_ID = SEQ_START + 7;
    public static final int LBM_ID = SEQ_START + 8;
    public static final Dish CRUMB_POTATOSHKA = new Dish(
            CP_ID
            , "CrumbPotatoshka"
            , LocalDate.now().minusDays(1)
            , new Restaurant(CRUMB_POTATO)
            , 1000.0);
    public static final Dish BELYASH_FOR_GENTS = new Dish(
            BFG_ID
            , "BelyashVIP"
            , LocalDate.now()
            , new Restaurant(BELYASH)
            , 800.0);
    public static final Dish LE_BIG_MAC = new Dish(
            LBM_ID
            , "LeBigMac"
            , LocalDate.now()
            , new Restaurant(MAXIM)
            , 10000.0);

    public static Dish getCreated() {
        return new Dish(
                null
                , "NewDish"
                , LocalDate.now().minusDays(1)
                , MAXIM
                , 20000.0);
    }

    public static Dish getUpdated() {
        return new Dish(
                CP_ID
                , "UpdatedDish"
                , LocalDate.now().minusDays(1)
                , MAXIM
                , 20000.0);
    }


    public static List<Dish> getAllDishes() {
        return Arrays.asList(CRUMB_POTATOSHKA, BELYASH_FOR_GENTS, LE_BIG_MAC);
    }

    public static List<DishWithVotes> getWithVotesForToday() {
        List<Vote> allVotes = VoteData.getAllVotes();
        return Arrays.asList(CRUMB_POTATOSHKA, BELYASH_FOR_GENTS, LE_BIG_MAC).stream()
                .filter(dish -> dish.getDate().isEqual(LocalDate.now()))
                .map(dish -> createWithVotes(dish, allVotes.stream()
                        .filter(vote -> vote.getDate().toLocalDate().isEqual(LocalDate.now()))
                        .filter(vote -> vote.getDishId() == dish.getId()).count())).collect(Collectors.toList());
    }

    private static DishWithVotes createWithVotes(Dish dish, long countOfVotes) {
        return new DishWithVotes(dish, countOfVotes);
    }

    public static List<DishWithVotes> getAllWithVotes() {
        List<Vote> allVotes = VoteData.getAllVotes();
        return Arrays.asList(CRUMB_POTATOSHKA, BELYASH_FOR_GENTS, LE_BIG_MAC).stream()
                .map(dish -> createWithVotes(dish, allVotes.stream()
                        .filter(vote -> vote.getDishId() == dish.getId()).count())).collect(Collectors.toList());
    }

}
           
                            