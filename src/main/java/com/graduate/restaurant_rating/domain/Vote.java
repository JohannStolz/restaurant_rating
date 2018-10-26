package com.graduate.restaurant_rating.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity {

    private int userId;

    private int dishId;

    private int restaurantId;
    @Column(nullable = false, unique = true)
    private LocalDateTime date;

    public Vote() {
    }

    public Vote(int userId, int dishId, int restaurantId, LocalDateTime date) {
        this.userId = userId;
        this.dishId = dishId;
        this.restaurantId = restaurantId;
        this.date = date;
    }

    public Vote(Integer id, int userId, int dishId, int restaurantId, LocalDateTime date) {
        super(id);
        this.userId = userId;
        this.dishId = dishId;
        this.restaurantId = restaurantId;
        this.date = date;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
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