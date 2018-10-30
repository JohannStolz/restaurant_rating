package com.graduate.restaurant_rating.service;

import com.graduate.restaurant_rating.repos.WinnerRepo;
import com.graduate.restaurant_rating.to.RestaurantIdWithCountOfVotes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WinnerServiceImpl implements WinnerService {

    private final WinnerRepo winnerRepo;

    @Autowired
    public WinnerServiceImpl(WinnerRepo winnerRepo) {
        this.winnerRepo = winnerRepo;
    }

    @Override
    public List<RestaurantIdWithCountOfVotes> findRestaurantWithVotes() {
        return winnerRepo.findRestaurantWithVotes();
    }


}
