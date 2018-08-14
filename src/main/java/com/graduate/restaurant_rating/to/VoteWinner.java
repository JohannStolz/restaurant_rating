package com.graduate.restaurant_rating.to;

import com.graduate.restaurant_rating.domain.Restaurant;

import java.time.LocalDate;

/**
 * Created by Johann Stolz 14.08.2018
 */
public class VoteWinner {
    private LocalDate date;
    private Restaurant restaurant;
    private boolean isWinner;

    public VoteWinner(LocalDate date, Restaurant restaurant, boolean isWinner) {
        this.date = date;
        this.restaurant = restaurant;
        this.isWinner = isWinner;
    }
}
