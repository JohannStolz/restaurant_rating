package com.graduate.restaurant_rating.repos;

import com.graduate.restaurant_rating.domain.Vote;
import com.graduate.restaurant_rating.to.RestaurantIdWithCountOfVotes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface WinnerRepo extends CrudRepository<Vote, Long> {
    @Query("SELECT " +
            "   new com.graduate.restaurant_rating.to.RestaurantIdWithCountOfVotes(v.restaurantId, COUNT(v)) " +
            "FROM " +
            "    Vote v " +
            "WHERE v.date >= CURRENT_DATE " +
            " GROUP BY  " +
            "    v.restaurantId"
    )
    List<RestaurantIdWithCountOfVotes> findRestaurantWithVotes();

}
