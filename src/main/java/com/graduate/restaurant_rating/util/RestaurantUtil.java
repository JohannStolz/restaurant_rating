package com.graduate.restaurant_rating.util;

import com.graduate.restaurant_rating.domain.Vote;
import com.graduate.restaurant_rating.to.RestaurantWithVotes;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class RestaurantUtil {
    public static List<RestaurantWithVotes> getWithVotes(Collection<Integer> restaurants, Collection<Vote> votes) {
        Map<Integer, Long> restaurantMap = new HashMap<>();
        votes.stream()
                .collect(Collectors.groupingBy(
                        Vote::getRestaurantId, Collectors.counting()))
                .forEach(restaurantMap::put);
        return restaurants.stream()
                .filter(restaurantMap::containsKey)
                .map(restaurant -> createWithVotes(restaurant, restaurantMap.get(restaurant)))
                .collect(toList());
    }

    public static RestaurantWithVotes createWithVotes(Integer restaurant_id, long countOfVotes) {
        return new RestaurantWithVotes(
                restaurant_id
                , countOfVotes);
    }
}

