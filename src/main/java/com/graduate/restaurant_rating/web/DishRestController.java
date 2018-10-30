package com.graduate.restaurant_rating.web;

import com.graduate.restaurant_rating.domain.Dish;
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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.graduate.restaurant_rating.util.Util.orElse;
import static com.graduate.restaurant_rating.util.ValidationUtil.checkNew;


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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    public List<Dish> getAll() {
        logger.info("Returning all Dishes");
        return service.getAll();
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
        startDate = startDate == null ? DateTimeUtil.MIN_DATE : startDate;
        endDate = endDate == null ? DateTimeUtil.MAX_DATE : endDate;
        startDate = startDate.isBefore(DateTimeUtil.MIN_DATE) ? DateTimeUtil.MIN_DATE : startDate;
        List<Dish> dishes = service.getAllByDate(
                orElse(startDate, DateTimeUtil.MIN_DATE), orElse(endDate, DateTimeUtil.MAX_DATE));
        LocalDate finalStartDate = startDate;
        LocalDate finalEndDate = endDate;
        logger.info("getBetween dates({} - {})", finalStartDate, finalEndDate);
        return dishes.stream()
                .map(d -> createWithVotes(service.get(d.getId()), getCountOfVotesForDishByDate(finalStartDate, finalEndDate, d.getId())))
                .sorted(Comparator.comparing(DishWithVotes::getCountOfVotes).reversed())
                .collect(Collectors.toList());
    }

    private DishWithVotes createWithVotes(Dish dish, long countOfVotes) {
        return new DishWithVotes(dish, countOfVotes);
    }

    private long getCountOfVotesForDishByDate(LocalDate startDate, LocalDate endDate, int dishId) {
        return voteService.findAllByDateBetweenAndDishId(startDate.atStartOfDay(), endDate.atTime(23, 59, 59), dishId).size();
    }
}