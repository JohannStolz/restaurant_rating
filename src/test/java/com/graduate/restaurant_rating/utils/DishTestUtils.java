package com.graduate.restaurant_rating.utils;

import com.graduate.restaurant_rating.domain.Dish;
import com.graduate.restaurant_rating.domain.Vote;
import com.graduate.restaurant_rating.testdata.DishData;
import com.graduate.restaurant_rating.testdata.VoteData;
import com.graduate.restaurant_rating.to.DishWithVotes;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by Johann Stolz 06.09.2018
 */
public class DishTestUtils {

    public static void main(String[] args) {
        List<Vote> votes = VoteData.getAllVotes();
        List<Dish> dishes = DishData.getAllDishes();
        votes.forEach(System.out::println);
        List<DishWithVotes> votesList = findWithVotes(dishes, votes);
        votesList.forEach(System.out::println);
    }

    public static List<DishWithVotes> findWithVotes(Collection<Dish> dishes, Collection<Vote> votes) {
        Map<Dish, Long> dishMap = new HashMap<>();
        votes.stream()
                .collect(Collectors.groupingBy(
                        Vote::getDish, Collectors.counting()))
                .forEach(dishMap::put);
        return dishes.stream()
                .map(dish -> createWithVotes(dish, dishMap.get(dish)))
                .collect(toList());
    }

    public static DishWithVotes createWithVotes(Dish dish, long countOfVotes) {
        return new DishWithVotes(
                dish.getId()
                , dish.getDescription()
                , LocalDate.now()
                , dish.getRestaurant().getId()
                , dish.getPrice()
                , countOfVotes);
    }
}