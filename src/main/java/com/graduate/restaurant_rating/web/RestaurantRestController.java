package com.graduate.restaurant_rating.web;

import com.graduate.restaurant_rating.domain.Restaurant;
import com.graduate.restaurant_rating.service.RestaurantService;
import com.graduate.restaurant_rating.to.RestaurantWithVotes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.graduate.restaurant_rating.util.ValidationUtil.checkNew;

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
        logger.info("Returning Restaurant by id: " + id);
        return service.get(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        logger.info("Deleting Restaurant by id" + id);
        service.delete(id);
    }

    @GetMapping()
    public List<Restaurant> getAll() {
        logger.info("Returning all Restaurants");
        return service.getAll();
    }

    @GetMapping("/withvotes")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<RestaurantWithVotes> getAllWithVotes() {
        logger.info("Returning all RestaurantsWithVotes");
        return service.getAllWithVotes();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Restaurant restaurant, @PathVariable("id") int id) {
        logger.info("Updating Restaurant by id");
        service.update(restaurant, id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
