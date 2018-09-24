package com.graduate.restaurant_rating.utils;

import com.graduate.restaurant_rating.domain.Dish;
import com.graduate.restaurant_rating.domain.Vote;
import com.graduate.restaurant_rating.testdata.DishData;
import com.graduate.restaurant_rating.testdata.VoteData;
import com.graduate.restaurant_rating.to.DishWithVotes;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.graduate.restaurant_rating.testdata.DishData.CRUMB_POTATOSHKA;
import static java.util.stream.Collectors.toList;

/**
 * Created by Johann Stolz 06.09.2018
 */
public class DishTestUtils {
    List<Vote> votes = VoteData.getAllVotes();
    List<Dish> dishes = DishData.getAllDishes();

    public static void main(String[] args) {
        //  DishTestUtils dishTestUtils = new DishTestUtils();
        // votes.forEach(System.out::println);
        // dishes.forEach(System.out::println);
        // List<DishWithVotes> votesList = findWithVotes(dishTestUtils.dishes, dishTestUtils.votes);
        // new DishTestUtils().encrytedPass();

    }

    public static List<DishWithVotes> findWithVotes(Collection<Dish> dishes, Collection<Vote> votes) {
        Map<Dish, Long> dishMap = new HashMap<>();
        votes.stream()
                .collect(Collectors.groupingBy(
                        Vote::getDish, Collectors.counting()))
                .forEach(dishMap::put);
        return dishes.stream()
                .filter(dishMap::containsKey)
                .map(dish -> createWithVotes(dish, dishMap.get(dish)))
                //.peek(System.out::println)
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

    public static List<DishWithVotes> getListWithVotes() {
        DishTestUtils dishTestUtils = new DishTestUtils();
        List<DishWithVotes> result = findWithVotes(dishTestUtils.dishes, dishTestUtils.votes);
        result.stream()
                .filter(a -> a.getDescription().equals(CRUMB_POTATOSHKA.getDescription()))
                .forEach(a -> a.setDate(LocalDate.now().minusDays(1)));
        return result;
    }

    public void encrytedPass() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encrytedPassword = passwordEncoder.encode("adminpass");
        System.out.println("Encoded password of adminpass=" + encrytedPassword);
        encrytedPassword = passwordEncoder.encode("user1pass");
        System.out.println("Encoded password of user1pass=" + encrytedPassword);
        encrytedPassword = passwordEncoder.encode("user2pass");
        System.out.println("Encoded password of user2pass=" + encrytedPassword);
    }


}
