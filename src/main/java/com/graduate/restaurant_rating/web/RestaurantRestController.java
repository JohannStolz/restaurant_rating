package com.graduate.restaurant_rating.web;

import com.graduate.restaurant_rating.domain.Restaurant;
import com.graduate.restaurant_rating.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.graduate.restaurant_rating.util.ValidationUtil.checkNew;

/**
 * Created by Johann Stolz 04.09.2018
 */
@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {
    private static final Logger logger = LoggerFactory.getLogger(RestaurantRestController.class);
    static final String REST_URL = "/profile/restaurants";
    private final RestaurantService service;


    @Autowired
    public RestaurantRestController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable("id") int id) {
        logger.info("Returning Restaurant by id");
        return service.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        logger.info("Deleting Restaurant by id");
        service.delete(id);
    }

    @GetMapping()
    public List<Restaurant> getAll() {
        logger.info("Returning all Restaurants");
        return service.getAll();
    }

    /*@PostMapping()
    public List<RestaurantWithVotes> getAllPost() {
        logger.info("getAllPost()");
        return service.getAll();
    }*/

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Restaurant restaurant, @PathVariable("id") int id) {
        logger.info("Updating Restaurant by id");
        service.update(restaurant, id);
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
        logger.info("Creating new Restaurant");
        checkNew(restaurant);
        Restaurant created = service.create(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }


}
