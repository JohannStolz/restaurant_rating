package com.graduate.restaurant_rating.web;

import com.graduate.restaurant_rating.domain.Restaurant;
import com.graduate.restaurant_rating.service.RestaurantService;
import com.graduate.restaurant_rating.service.WinnerService;
import com.graduate.restaurant_rating.to.RestaurantIdWithCountOfVotes;
import com.graduate.restaurant_rating.to.RestaurantWithVotes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = WinnerRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class WinnerRestController {
    private static final Logger logger = LoggerFactory.getLogger(WinnerRestController.class);
    static final String REST_URL = "/profile/winner";
    private final WinnerService service;
    private final RestaurantService restaurantService;

    @Autowired
    public WinnerRestController(WinnerService service, RestaurantService restaurantService) {
        this.service = service;
        this.restaurantService = restaurantService;
    }

    @GetMapping()
    public List<RestaurantWithVotes> get() {
        logger.info("WinnerRestController get");
        return getRestaurantWithVotes();
    }

    @PostMapping()
    public List<RestaurantWithVotes> successForwardUrl() {
        logger.info("WinnerRestController successForwardUrl");
        return getRestaurantWithVotes();
    }


    private List<RestaurantWithVotes> getRestaurantWithVotes() {
        return makeWithVotes(service.findRestaurantWithVotes());
    }

    private List<RestaurantWithVotes> makeWithVotes(List<RestaurantIdWithCountOfVotes> list) {
        return list.stream()
                .map(r -> createWithVotes(restaurantService.get(r.getRestaurant()), r.getVotes()))
                .sorted(Comparator.comparing(RestaurantWithVotes::getCountOfVotes).reversed())
                .collect(Collectors.toList());
    }

    private RestaurantWithVotes createWithVotes(Restaurant restaurant, long countOfVotes) {
        return new RestaurantWithVotes(
                restaurant
                , countOfVotes);
    }
}
