package com.graduate.restaurant_rating.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Johann Stolz 14.08.2018
 */
@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractBaseEntity {
    private String name;
    private String address;
    private String email;
    //private RestaurantMenu menu;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, String address, String email) {
        super(id);
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public Restaurant(Restaurant restaurant) {
        this(restaurant.id, restaurant.name, restaurant.address, restaurant.email);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return super.getId();
    }

    /*public RestaurantMenu getMenu() {
        return menu;
    }

    public void setMenu(RestaurantMenu menu) {
        this.menu = menu;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                '}';
    }

}
