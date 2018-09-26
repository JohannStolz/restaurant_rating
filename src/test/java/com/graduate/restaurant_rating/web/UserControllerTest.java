package com.graduate.restaurant_rating.web;

import com.graduate.restaurant_rating.domain.User;
import com.graduate.restaurant_rating.service.UserService;
import com.graduate.restaurant_rating.testdata.UserData;
import com.graduate.restaurant_rating.web.json.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;

import static com.graduate.restaurant_rating.testdata.UserData.*;
import static com.graduate.restaurant_rating.utils.TestUtil.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends AbstractControllerTest {
    private String REST_URL = UserRestController.REST_URL + "/";
    private ArrayList<User> all = new ArrayList<>(UserData.getAllUsers());
    @Autowired
    private UserService service;


    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(UserData.getAllUsers()));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + USER1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());
        all.remove(USER1);
        assertMatchUsers(service.getAll(), all);
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(UserData.getAllUsers()));
    }

    @Test
    public void testUpdate() throws Exception {
        User updated = getUpdated();
        mockMvc.perform(put(REST_URL + USER1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(user("user1").password("user1pass")))
                .andExpect(status().isNoContent());
        assertMatchUser(service.get(USER1_ID), updated);

    }

    @Test
    public void testCreate() throws Exception {
        User created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL + "save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(ADMIN)));
        User returned = readFromJson(action, User.class);
        created.setId(returned.getId());
        assertMatchUser(returned, created);
    }
}
