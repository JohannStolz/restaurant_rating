package com.graduate.restaurant_rating.util;

import com.graduate.restaurant_rating.domain.Vote;
import com.graduate.restaurant_rating.to.DishWithVotes;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


@Component
public class DishUtil {

    public static List<DishWithVotes> getWithVotes(Map<Integer, LocalDate> map, Collection<Vote> votes) {
        Map<Integer, Long> dishMap = new HashMap<>();
        votes.stream()
                .collect(Collectors.groupingBy(
                        Vote::getDishId, Collectors.counting()))
                .forEach(dishMap::put);
        List<Integer> dishesId = new ArrayList<>(map.keySet());
        return dishesId.stream()
                .filter(dishMap::containsKey)
                .map(dish -> createWithVotes(dish, map.get(dish), dishMap.get(dish)))
                .collect(toList());
    }

    public static DishWithVotes createWithVotes(Integer dishId, LocalDate date, long countOfVotes) {
        return new DishWithVotes(
                dishId
                , date
                , countOfVotes);
    }
}
