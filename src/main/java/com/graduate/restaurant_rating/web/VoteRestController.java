package com.graduate.restaurant_rating.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Johann Stolz 04.09.2018
 */

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {
    private static final Logger logger = LoggerFactory.getLogger(VoteRestController.class);
    static final String REST_URL = "/profile/votes";
}
