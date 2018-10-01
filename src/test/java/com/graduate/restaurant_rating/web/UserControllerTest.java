package com.graduate.restaurant_rating.web;

import com.graduate.restaurant_rating.domain.User;
import com.graduate.restaurant_rating.service.UserService;
import com.graduate.restaurant_rating.testdata.UserData;
import com.graduate.restaurant_rating.web.json.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;

import static com.graduate.restaurant_rating.testdata.RestaurantData.CRUMB_POTATO_ID;
import static com.graduate.restaurant_rating.testdata.UserData.*;
import static com.graduate.restaurant_rating.utils.TestUtil.contentJson;
import static com.graduate.restaurant_rating.utils.TestUtil.readFromJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest extends AbstractControllerTest {
    private String REST_URL = UserRestController.REST_URL + "/";
    private ArrayList<User> all = new ArrayList<>(UserData.getAllUsers());
    @Autowired
    private UserService service;


    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + USER2_ID)
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(USER2));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + USER1_ID)
        )
                .andExpect(status().isNoContent());
        all.remove(USER1);
        assertMatchUsers(service.getAll(), all);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(UserData.getAllUsers()));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdate() throws Exception {
        User updated = getUpdated();
        mockMvc.perform(put(REST_URL + USER1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());
        assertMatchUser(service.get(USER1_ID), updated);

    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testCreate() throws Exception {
        User created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL + "save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)));
        User returned = readFromJson(action, User.class);
        created.setId(returned.getId());
        assertMatchUser(returned, created);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetInvalidId() throws Exception {
        mockMvc.perform(get(REST_URL + CRUMB_POTATO_ID))
                .andExpect(jsonPath("$.errorCode").value(404))
                .andExpect(jsonPath("$.message").value("Not found entity with id=" + CRUMB_POTATO_ID))
                .andDo(print());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetInvalidArgument() throws Exception {
        mockMvc.perform(get(REST_URL + "f"))
                .andExpect(jsonPath("$.errorCode").value(400))
                .andExpect(jsonPath("$.message").value("The request could not be understood by the server due to malformed syntax."))
                .andDo(print());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdateInvalidId() throws Exception {
        mockMvc.perform(put(REST_URL + CRUMB_POTATO_ID))
                .andExpect(jsonPath("$.errorCode").value(400))
                .andExpect(jsonPath("$.message").value("The request could not be understood by the server due to malformed syntax."))
                .andDo(print());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGetAllUnAuth() throws Exception {
        mockMvc.perform(get(REST_URL)
        )
                .andExpect(status().isBadRequest())
                .andDo(print())
        ;
    }

}
