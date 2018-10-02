package com.graduate.restaurant_rating.web;


import com.graduate.restaurant_rating.domain.Vote;
import com.graduate.restaurant_rating.service.VoteService;
import com.graduate.restaurant_rating.testdata.VoteData;
import com.graduate.restaurant_rating.web.json.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.graduate.restaurant_rating.testdata.RestaurantData.CRUMB_POTATO;
import static com.graduate.restaurant_rating.testdata.RestaurantData.CRUMB_POTATO_ID;
import static com.graduate.restaurant_rating.testdata.UserData.ADMIN;
import static com.graduate.restaurant_rating.testdata.UserData.ADMIN_ID;
import static com.graduate.restaurant_rating.testdata.VoteData.*;
import static com.graduate.restaurant_rating.utils.TestUtil.assertMatch;
import static com.graduate.restaurant_rating.utils.TestUtil.readFromJson;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class VoteControllerTest extends AbstractControllerTest {
    private String REST_URL = VoteRestController.REST_URL + "/";
    private ArrayList<Vote> all = new ArrayList<>(VoteData.getAllVotes());
    @Autowired
    private VoteService service;

    @WithMockUser(roles = "ADMIN")
    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + ADMIN_VOTE_ID)
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(ADMIN_VOTE_ID))
                .andExpect(jsonPath("$.user.id").value(ADMIN_ID))
                .andExpect(jsonPath("$.restaurant.name").value(CRUMB_POTATO.getName()));
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + USER1_VOTE_ID)
        )
                .andExpect(status().isNoContent());
        all.remove(USER1_VOTE);
        List<Vote> actual = service.getAll();
        getVotesWithTruncatedLocaleDateTime(actual, all);
        assertMatch(actual, all);
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$.[0].id").value(ADMIN_VOTE_ID))
                .andExpect(jsonPath("$.[1].id").value(USER1_VOTE_ID))
                .andExpect(jsonPath("$.[0].user.name").value(ADMIN.getName()));
    }

    @WithMockUser(roles = "USER")
    @Test
    public void testUpdate() throws Exception {
        Vote updated = getUpdated();
        mockMvc.perform(put(REST_URL + USER1_VOTE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());
        assertMatch(service.get(USER1_VOTE_ID), updated);

    }

    @WithMockUser(roles = "USER")
    @Test
    public void testCreate() throws Exception {
        Vote created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL + "save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
        );
        Vote returned = readFromJson(action, Vote.class);
        created.setId(returned.getId());
        assertMatch(returned, created);
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void testGetInvalidId() throws Exception {
        mockMvc.perform(get(REST_URL + CRUMB_POTATO_ID))
                .andExpect(jsonPath("$.errorCode").value(404))
                .andExpect(jsonPath("$.message").value("Not found entity with id=" + CRUMB_POTATO_ID))
                .andDo(print());
    }

    @WithMockUser(roles = "USER")
    @Test
    public void testGetInvalidArgument() throws Exception {
        mockMvc.perform(get(REST_URL + "f"))
                .andExpect(jsonPath("$.errorCode").value(400))
                .andExpect(jsonPath("$.message").value("The request could not be understood by the server: Failed to convert value of type 'java.lang.String' to required type 'int'; nested exception is java.lang.NumberFormatException: For input string: \"f\""))
                .andDo(print());
    }

    @WithMockUser(roles = "USER")
    @Test
    public void testUpdateInvalidId() throws Exception {
        mockMvc.perform(put(REST_URL + CRUMB_POTATO_ID))
                .andExpect(jsonPath("$.errorCode").value(400))
                .andExpect(jsonPath("$.message").value("The request could not be understood by the server: Content type '' not supported"))
                .andDo(print());
    }

    @WithMockUser(roles = "USER")
    @Test
    public void testUpdateReVote() throws Exception {
        Vote updated = getUpdatedUser2();
        if (LocalDateTime.now().toLocalTime().isBefore(END_OF_VOTING)) {
            mockMvc.perform(put(REST_URL + USER2_SECOND_VOTE_ID)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtil.writeValue(updated)))
                    .andExpect(jsonPath("$.errorCode").value(400))
                    .andExpect(jsonPath("$.message").value("The request could not be understood by the server: The time for re-voting is up to 11 hours"))
                    .andDo(print());
        } else {
            mockMvc.perform(put(REST_URL + USER2_VOTE_ID)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtil.writeValue(updated)))
                    .andExpect(jsonPath("$.errorCode").value(400))
                    .andExpect(jsonPath("$.message").value("The request could not be understood by the server: The time for re-voting is up to 11 hours"))
                    .andDo(print());
        }
    }
}
