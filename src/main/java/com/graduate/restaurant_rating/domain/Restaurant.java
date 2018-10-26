package com.graduate.restaurant_rating.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractBaseEntity {
    private String name;
    private String address;
    @Column(nullable = false, unique = true)
    private String email;

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
