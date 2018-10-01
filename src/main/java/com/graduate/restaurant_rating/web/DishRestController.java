package com.graduate.restaurant_rating.web;

import com.graduate.restaurant_rating.domain.Dish;
import com.graduate.restaurant_rating.domain.Vote;
import com.graduate.restaurant_rating.service.DishService;
import com.graduate.restaurant_rating.service.VoteService;
import com.graduate.restaurant_rating.to.DishWithVotes;
import com.graduate.restaurant_rating.util.DateTimeUtil;
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
import java.time.LocalDate;
import java.util.List;

import static com.graduate.restaurant_rating.util.DishUtil.getWithVotes;
import static com.graduate.restaurant_rating.util.Util.orElse;
import static com.graduate.restaurant_rating.util.ValidationUtil.checkNew;

/**
 * Created by Johann Stolz 04.09.2018
 */

@RestController
@RequestMapping(value = DishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController {
    private static final Logger logger = LoggerFactory.getLogger(DishRestController.class);
    static final String REST_URL = "/profile/dishes";
    private final DishService service;
    private final VoteService voteService;

    @Autowired
    public DishRestController(DishService service, VoteService voteService) {
        this.service = service;
        this.voteService = voteService;
    }

    @GetMapping("/{id}")
    public Dish get(@PathVariable("id") int id) {
        logger.info("Returning Dish by id: " + id);
        return service.get(id);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        logger.info("Deleting Dish by id :" + id);
        service.delete(id);
    }

    @GetMapping()
    public List<Dish> getAll() {
        logger.info("Returning all Dishes");
        return service.getAll();
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping()
    public List<DishWithVotes> getAllPost() {
        logger.info("getAllWithVotes");
        return service.getAllWithVotes();
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Dish dish, @PathVariable("id") int id) {
        logger.info("Updating Dish by id");
        service.update(dish, id);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create(@RequestBody Dish dish) {
        logger.info("Creating new Dish");
        checkNew(dish);
        Dish created = service.create(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(value = "/filter")
    public List<DishWithVotes> getBetween(
            @RequestParam(value = "startDate", required = false) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) LocalDate endDate) {
        logger.info("getBetween dates({} - {})", startDate, endDate);
        List<Dish> dishesDateFiltered = service.getAllByDate(
                orElse(startDate, DateTimeUtil.MIN_DATE), orElse(endDate, DateTimeUtil.MAX_DATE));
        List<Vote> votes = voteService.getAll();
        return getWithVotes(dishesDateFiltered, votes);
    }

}
   