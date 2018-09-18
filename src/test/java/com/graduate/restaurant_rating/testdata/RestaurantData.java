package com.graduate.restaurant_rating.testdata;

import com.graduate.restaurant_rating.domain.Restaurant;

import java.util.Arrays;
import java.util.List;

import static com.graduate.restaurant_rating.domain.AbstractBaseEntity.SEQ_START;

/**
 * Created by Johann Stolz 05.09.2018
 */
public class RestaurantData {

    public static final int CRUMB_POTATO_ID = SEQ_START + 3;
    public static final int BELYASH_ID = SEQ_START + 4;
    public static final int MAXIM_ID = SEQ_START + 5;

    public final static Restaurant CRUMB_POTATO = new Restaurant(
            CRUMB_POTATO_ID
            , "CrumbPotato"
            , "Bus station"
            , "CP@gmail.com");
    public final static Restaurant BELYASH = new Restaurant(
            BELYASH_ID
            , "Belyash for gentlemen"
            , "Sub"
            , "Sub@gmail.com");
    public final static Restaurant MAXIM = new Restaurant(
            MAXIM_ID
            , "Maxim"
            , "Paris"
            , "Maxim@gmail.com");

    public static Restaurant getCreated() {
        return new Restaurant(
                null
                , "Ruomochnaya"
                , "Square"
                , "Ruomka@gmail.com");
    }
    public static Restaurant getUpdated(){
        return new Restaurant(
                MAXIM_ID
                , "Ruomochnaya u Maxima"
                , "Paris"
                , "Maxim@gmail.com");

    }

    public static List<Restaurant> getAllRestaurants(){
        return Arrays.asList(CRUMB_POTATO, BELYASH, MAXIM);
    }
}
