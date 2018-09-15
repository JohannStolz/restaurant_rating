package com.graduate.restaurant_rating.web;

import com.graduate.restaurant_rating.domain.Dish;
import com.graduate.restaurant_rating.repos.DishRepo;
import com.graduate.restaurant_rating.service.DishService;
import com.graduate.restaurant_rating.to.DishWithVotes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Johann Stolz 04.09.2018
 */

@RestController
@RequestMapping(value = DishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController {
    private static final Logger logger = LoggerFactory.getLogger(DishRestController.class);
    static final String REST_URL = "/profile/dishes";
    private final DishService dishService;
    @Autowired
    public DishRepo dishRepo;

    @Autowired
    public DishRestController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("/{id}")
    public Dish get(@PathVariable("id") int id) {
        logger.info("Returning Dish by id");
        return dishService.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        logger.info("Deleting Dish by id");
        dishService.delete(id);
    }

    @GetMapping()
    public List<Dish> getAll() {
        logger.info("Returning all DishWithVotes");
        // return dishService.getAll();
        //return dishService.getAllWithVotes();
        return dishRepo.findAll();
    }

    @PostMapping()
    public List<Dish> getAllPost() {
        logger.info("getAllPost()");
        // return dishService.getAll();
        //return dishService.getAllWithVotes();
        return dishRepo.findAll();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Dish dish, @PathVariable("id") int id) {
        logger.info("Updating Dish by id");
        dishService.update(dish, id);
    }

    /*@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Dish> create(@RequestBody Dish dish) {
        logger.info("Creating new Dish");
        Dish created = dishService.create(dish);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }*/

    @GetMapping(value = "/filter")
    public List<DishWithVotes> getBetween(
            @RequestParam(value = "startDate", required = false) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) LocalDate endDate) {
        logger.info("getBetween dates({} - {})", startDate, endDate);
        return null;
    }

}
