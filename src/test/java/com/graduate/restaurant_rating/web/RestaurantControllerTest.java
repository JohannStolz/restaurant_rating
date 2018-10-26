package com.graduate.restaurant_rating.web;

import com.graduate.restaurant_rating.domain.Restaurant;
import com.graduate.restaurant_rating.service.RestaurantService;
import com.graduate.restaurant_rating.testdata.DishData;
import com.graduate.restaurant_rating.testdata.RestaurantData;
import com.graduate.restaurant_rating.utils.DishAndRestaurantsTestUtils;
import com.graduate.restaurant_rating.web.json.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static com.graduate.restaurant_rating.testdata.RestaurantData.*;
import static com.graduate.restaurant_rating.testdata.UserData.ADMIN_ID;
import static com.graduate.restaurant_rating.utils.TestUtil.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RestaurantControllerTest extends AbstractControllerTest {
    private String REST_URL = RestaurantRestController.REST_URL + "/";
    private List<Restaurant> all = new ArrayList<>(RestaurantData.getAllRestaurants());
    @Autowired
    private RestaurantService service;


    @Test
    @WithMockUser(roles = "USER")
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + MAXIM_ID)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MAXIM));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + CRUMB_POTATO_ID)
        )
                .andExpect(status().isNoContent());
        all.remove(CRUMB_POTATO);
        assertMatch(service.getAll(), all);
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RestaurantData.getAllRestaurants()));
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetAllWithVotes() throws Exception {
        mockMvc.perform(get(REST_URL+ "/withvotes"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(DishAndRestaurantsTestUtils.getRestaurantListWithVotes()));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdate() throws Exception {
        Restaurant updated = getUpdated();
        mockMvc.perform(put(REST_URL + MAXIM_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
        )
                .andExpect(status().isNoContent());
        assertMatch(service.get(MAXIM_ID), updated);

    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testCreate() throws Exception {
        Restaurant created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL + "save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
        );
        Restaurant returned = readFromJson(action, Restaurant.class);
        created.setId(returned.getId());
        assertMatch(returned, created);
    }

    @Test
    public void testGetInvalidId() throws Exception {
        mockMvc.perform(get(REST_URL + ADMIN_ID))
                .andExpect(jsonPath("$.errorCode").value(404))
                .andExpect(jsonPath("$.message").value("Not found entity with id=" + ADMIN_ID));

    }

    @Test
    public void testGetInvalidArgument() throws Exception {
        mockMvc.perform(get(REST_URL + "f"))
                .andExpect(jsonPath("$.errorCode").value(400))
                .andExpect(jsonPath("$.message").value("The request could not be understood by the server: Failed to convert value of type 'java.lang.String' to required type 'int'; nested exception is java.lang.NumberFormatException: For input string: \"f\""));

    }

    @Test
    public void testUpdateInvalidId() throws Exception {
        mockMvc.perform(put(REST_URL + ADMIN_ID))
                .andExpect(jsonPath("$.errorCode").value(400))
                .andExpect(jsonPath("$.message").value("The request could not be understood by the server: Content type '' not supported"));

    }

}
