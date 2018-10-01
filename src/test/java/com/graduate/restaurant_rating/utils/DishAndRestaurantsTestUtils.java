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

import static com.graduate.restaurant_rating.testdata.DishData.CRUMB_POTATOSHKA;

/**
 * Created by Johann Stolz 06.09.2018
 */
public class DishAndRestaurantsTestUtils {
    private static List<Vote> votes = VoteData.getAllVotes();
    private static List<Dish> dishes = DishData.getAllDishes();
    private static List<Restaurant> restaurants = RestaurantData.getAllRestaurants();


    public static List<DishWithVotes> getDishListWithVotes() {
        List<DishWithVotes> result = DishUtil.getWithVotes(dishes, votes);
        result.stream()
                .filter(a -> a.getDescription().equals(CRUMB_POTATOSHKA.getDescription()))
                .forEach(a -> a.setDate(LocalDate.now().minusDays(1)));
        return result;
    }

    public static List<RestaurantWithVotes> getRestaurantListWithVotes() {
        return RestaurantUtil.getWithVotes(restaurants, votes);
    }

}
