package com.graduate.restaurant_rating.util;

import com.graduate.restaurant_rating.domain.Dish;
import com.graduate.restaurant_rating.domain.Vote;
import com.graduate.restaurant_rating.to.DishWithVotes;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by Johann Stolz 06.09.2018
 */
public class DishUtil {

    public static List<DishWithVotes> getWithVotes(Collection<Dish> dishes, Collection<Vote> votes) {
        Map<Dish, Long> dishMap = new HashMap<>();
        votes.stream()
                .collect(Collectors.groupingBy(
                        Vote::getDish, Collectors.counting()))
                .forEach(dishMap::put);
        return dishes.stream()
                .filter(dishMap::containsKey)
                .map(dish -> createWithVotes(dish, dishMap.get(dish)))
                .collect(toList());
    }

    public static DishWithVotes createWithVotes(Dish dish, long countOfVotes) {
        return new DishWithVotes(
                dish.getId()
                , dish.getDescription()
                , dish.getDate()
                , dish.getRestaurant().getId()
                , dish.getPrice()
                , countOfVotes);
    }
}
