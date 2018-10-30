package com.graduate.restaurant_rating.to;

import com.graduate.restaurant_rating.domain.Restaurant;

import java.util.Objects;

public class RestaurantWithVotes {
    private Restaurant restaurant;
    private long countOfVotes;

    public RestaurantWithVotes() {
    }

    public RestaurantWithVotes(Restaurant restaurant, long countOfVotes) {
        this.restaurant = restaurant;
        this.countOfVotes = countOfVotes;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public long getCountOfVotes() {
        return countOfVotes;
    }

    public void setCountOfVotes(long countOfVotes) {
        this.countOfVotes = countOfVotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantWithVotes that = (RestaurantWithVotes) o;
        return getCountOfVotes() == that.getCountOfVotes() &&
                Objects.equals(getRestaurant(), that.getRestaurant());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRestaurant(), getCountOfVotes());
    }
}
