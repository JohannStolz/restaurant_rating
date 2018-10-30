package com.graduate.restaurant_rating.service;

import com.graduate.restaurant_rating.to.RestaurantIdWithCountOfVotes;

import java.util.List;

public interface WinnerService {
    List<RestaurantIdWithCountOfVotes> findRestaurantWithVotes();

}
