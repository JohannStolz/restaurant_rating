package com.graduate.restaurant_rating.web;


import com.graduate.restaurant_rating.domain.Vote;
import com.graduate.restaurant_rating.service.VoteService;
import com.graduate.restaurant_rating.util.SecurityUtil;
import com.graduate.restaurant_rating.util.ValidationUtil;
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

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {
    private static final Logger logger = LoggerFactory.getLogger(VoteRestController.class);
    static final String REST_URL = "/profile/votes";
    private final VoteService service;
    private final SecurityUtil securityUtil;

    @Autowired
    public VoteRestController(VoteService service, SecurityUtil securityUtil) {
        this.service = service;
        this.securityUtil = securityUtil;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public Vote get(@PathVariable("id") int id) {
        logger.info("Returning Vote by id");
        return service.get(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        logger.info("Deleting Vote by id");
        service.delete(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    public List<Vote> getAll() {
        logger.info("Returning all Votes");
        return service.getAll();
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Vote update(@PathVariable int id, @RequestBody Vote vote) {
        int userId = securityUtil.authUserId();
        ValidationUtil.assureIdConsistent(vote, id);
        logger.info("Creating new Vote ");
        return service.update(vote, userId);
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> save(@RequestBody Vote vote) {
        Vote created = service.create(vote);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
