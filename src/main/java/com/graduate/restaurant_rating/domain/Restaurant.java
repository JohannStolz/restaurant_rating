package com.graduate.restaurant_rating.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

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
                "address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getAddress(), that.getAddress()) &&
                Objects.equals(getEmail(), that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName(), getAddress(), getEmail());
    }
}
