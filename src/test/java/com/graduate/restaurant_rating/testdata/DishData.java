package com.graduate.restaurant_rating.testdata;

import com.graduate.restaurant_rating.domain.Dish;
import com.graduate.restaurant_rating.domain.Restaurant;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.graduate.restaurant_rating.domain.AbstractBaseEntity.SEQ_START;
import static com.graduate.restaurant_rating.testdata.RestaurantData.*;

/**
 * Created by Johann Stolz 05.09.2018
 */
public class DishData {
    public static final int CP_ID = SEQ_START + 6;
    public static final int BFG_ID = SEQ_START + 7;
    public static final int LBM_ID = SEQ_START + 8;
    public static final Dish CRUMB_POTATOSHKA = new Dish(
            CP_ID
            , "CrumbPotatoshka"
            , LocalDate.now()
            , new Restaurant(CRUMB_POTATO)
            , 1000.0);
    public static final Dish BELYASH_FOR_GENTS = new Dish(
            BFG_ID
            , "BelyashVIP"
            , LocalDate.now()
            , new Restaurant(BELYASH)
            , 800.0);
    public static final Dish LE_BIG_MAC = new Dish(
            LBM_ID
            , "LeBigMac"
            , LocalDate.now()
            , new Restaurant(MAXIM)
            , 10000.0);

    public static Dish getCreated() {
        return new Dish(
                null
                , "NewDish"
                , LocalDate.now()
                , new Restaurant(MAXIM)
                , 20000.0);
    }

    public static Dish getUpdated() {
        return new Dish(
                CP_ID
                , "UpdatedDish"
                , LocalDate.now()
                , new Restaurant(MAXIM)
                , 20000.0);
    }


    public static List<Dish> getAllDishes() {
        return Arrays.asList(CRUMB_POTATOSHKA, BELYASH_FOR_GENTS, LE_BIG_MAC);
    }

}
                            