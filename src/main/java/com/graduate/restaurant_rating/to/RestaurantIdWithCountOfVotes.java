package com.graduate.restaurant_rating.to;


public class RestaurantIdWithCountOfVotes {
    private int restaurant;
    private long votes;


    public RestaurantIdWithCountOfVotes(int restaurant, long votes) {
        this.restaurant = restaurant;
        this.votes = votes;
    }

    public int getRestaurant() {
        return restaurant;
    }

    public long getVotes() {
        return votes;
    }

}
