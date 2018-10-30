package com.graduate.restaurant_rating.web;

import com.graduate.restaurant_rating.service.WinnerService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static com.graduate.restaurant_rating.testdata.RestaurantData.BELYASH_ID;
import static com.graduate.restaurant_rating.testdata.RestaurantData.MAXIM_ID;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class WinnerControllerTest extends AbstractControllerTest {
    private String REST_URL = WinnerRestController.REST_URL;

    @Autowired
    private WinnerService service;


    @WithMockUser(roles = "ADMIN")
    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$.[0].countOfVotes").value(2))
                .andExpect(jsonPath("$.[1].countOfVotes").value(1))
                .andExpect(jsonPath("$.[0].restaurant.id").value(BELYASH_ID))
                .andExpect(jsonPath("$.[1].restaurant.id").value(MAXIM_ID))
        ;
    }

}