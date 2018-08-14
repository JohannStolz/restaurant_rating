package com.graduate.restaurant_rating.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * Created by Johann Stolz 14.08.2018
 */

@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity {
    private int userId;
    private int dishId;
    private int restaurantId;
    private LocalDate date = LocalDate.now();

    public Vote() {
    }

    public Vote(Integer id, int userId, int dishId, int restaurantId, LocalDate date) {
        super(id);
        this.userId = userId;
        this.dishId = dishId;
        this.restaurantId = restaurantId;
        this.date = date;
    }

    public Vote(Vote vote) {
        this(vote.id, vote.dishId, vote.userId, vote.restaurantId, vote.date);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "userId=" + userId +
                ", dishId=" + dishId +
                ", restaurantId=" + restaurantId +
                ", date=" + date +
                ", id=" + id +
                '}';
    }
}
