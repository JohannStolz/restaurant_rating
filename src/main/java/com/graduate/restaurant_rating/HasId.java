package com.graduate.restaurant_rating;

/**
 * Created by Johann Stolz 07.09.2018
 */
public interface HasId {

    Integer getId();

    void setId(Integer id);

    default boolean isNew() {
        return getId() == null;
    }
}
