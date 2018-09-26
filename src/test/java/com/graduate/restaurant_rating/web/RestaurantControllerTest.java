package com.graduate.restaurant_rating.web;

import com.graduate.restaurant_rating.domain.Restaurant;
import com.graduate.restaurant_rating.service.RestaurantService;
import com.graduate.restaurant_rating.testdata.RestaurantData;
import com.graduate.restaurant_rating.web.json.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;

import static com.graduate.restaurant_rating.testdata.RestaurantData.*;
import static com.graduate.restaurant_rating.testdata.UserData.ADMIN;
import static com.graduate.restaurant_rating.utils.TestUtil.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RestaurantControllerTest extends AbstractControllerTest {
    private String REST_URL = RestaurantRestController.REST_URL + "/";
    private ArrayList<Restaurant> all = new ArrayList<>(RestaurantData.getAllRestaurants());
    @Autowired
    private RestaurantService service;


    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + MAXIM_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MAXIM));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + CRUMB_POTATO_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());
        all.remove(CRUMB_POTATO);
        assertMatch(service.getAll(), all);
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RestaurantData.getAllRestaurants()));
    }

    @Test
    public void testUpdate() throws Exception {
        Restaurant updated = getUpdated();
        mockMvc.perform(put(REST_URL + MAXIM_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(user("user1").password("user1pass"))) /// PAY ATTENTION
                .andExpect(status().isNoContent());
        assertMatch(service.get(MAXIM_ID), updated);

    }

    @Test
    public void testCreate() throws Exception {
        Restaurant created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL + "save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(ADMIN)));
        Restaurant returned = readFromJson(action, Restaurant.class);
        created.setId(returned.getId());
        assertMatch(returned, created);
    }

}
