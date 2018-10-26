package com.graduate.restaurant_rating.to;

import com.graduate.restaurant_rating.HasId;

import java.util.Objects;

public class RestaurantWithVotes implements HasId {
    protected Integer id;
    private String name;
    private String address;
    private long countOfVotes;

    public RestaurantWithVotes() {
    }

    public RestaurantWithVotes(Integer id, /*String name*/ long countOfVotes) {
        this.id = id;
        this.name = name;
        this.countOfVotes = countOfVotes;
    }

    public long getCountOfVotes() {
        return countOfVotes;
    }


    public String getAddress() {
        return address;
    }


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantWithVotes that = (RestaurantWithVotes) o;
        return getCountOfVotes() == that.getCountOfVotes() &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getAddress(), that.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAddress(), getCountOfVotes());
    }
}
