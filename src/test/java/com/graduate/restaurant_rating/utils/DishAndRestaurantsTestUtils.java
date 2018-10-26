package com.graduate.restaurant_rating.utils;

import com.graduate.restaurant_rating.domain.Dish;
import com.graduate.restaurant_rating.domain.Restaurant;
import com.graduate.restaurant_rating.domain.Vote;
import com.graduate.restaurant_rating.testdata.DishData;
import com.graduate.restaurant_rating.testdata.RestaurantData;
import com.graduate.restaurant_rating.testdata.VoteData;
import com.graduate.restaurant_rating.to.DishWithVotes;
import com.graduate.restaurant_rating.to.RestaurantWithVotes;
import com.graduate.restaurant_rating.util.DishUtil;
import com.graduate.restaurant_rating.util.RestaurantUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.graduate.restaurant_rating.testdata.DishData.CRUMB_POTATOSHKA;

public class DishAndRestaurantsTestUtils {
    private static List<Vote> votes = VoteData.getAllVotes();
    private static List<Dish> dishes = DishData.getAllDishes();
    private static List<Restaurant> restaurants = RestaurantData.getAllRestaurants();
    private static List<Integer> restaurantsId = restaurants.stream().map(Restaurant::getId).collect(Collectors.toList());


    public static List<DishWithVotes> getDishListWithVotes() {
        Map<Integer, LocalDate> map = dishes.stream()
                .collect(Collectors.toMap(Dish::getId, Dish::getDate));
        List<DishWithVotes> result = DishUtil.getWithVotes(map, votes);
        result.stream().forEach(a -> a.setDateTime(LocalDate.now()));
        result.stream()
                .filter(a -> a.getId().equals(CRUMB_POTATOSHKA.getId()))
                .forEach(a -> a.setDateTime(LocalDate.now().minusDays(1)));
        return result;
    }

    public static List<RestaurantWithVotes> getRestaurantListWithVotes() {
        return RestaurantUtil.getWithVotes(restaurantsId, votes);
    }

}
