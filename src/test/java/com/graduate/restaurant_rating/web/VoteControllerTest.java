package com.graduate.restaurant_rating.web;


import com.graduate.restaurant_rating.domain.Vote;
import com.graduate.restaurant_rating.service.VoteService;
import com.graduate.restaurant_rating.testdata.DishData;
import com.graduate.restaurant_rating.testdata.VoteData;
import com.graduate.restaurant_rating.web.json.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class VoteControllerTest extends AbstractControllerTest {
    private String REST_URL = VoteRestController.REST_URL + "/";
    private ArrayList<Vote> all = new ArrayList<>(VoteData.getAllVotes());
    @Autowired
    private VoteService service;
    private final int TEN_O_CLOCK = 10;
    private final int ELEVEN_O_CLOCK = 11;
    private final int TWELVE_O_CLOCK = 12;
    private LocalDateTime TEN_O_CLOCK_DATE = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS).plusHours(TEN_O_CLOCK);
    private LocalDateTime ELEVEN_O_CLOCK_DATE = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS).plusHours(ELEVEN_O_CLOCK);
    private LocalDateTime TWELVE_O_CLOCK_DATE = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS).plusHours(TWELVE_O_CLOCK);

    @WithMockUser(roles = "ADMIN")
    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + ADMIN_VOTE_ID)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value(ADMIN_VOTE_ID))
                .andExpect(jsonPath("userId").value(ADMIN_ID))
                .andExpect(jsonPath("restaurantId").value(CRUMB_POTATO.getId()));
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
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$.[0].id").value(ADMIN_VOTE_ID))
                .andExpect(jsonPath("$.[1].id").value(USER1_VOTE_ID))
                .andExpect(jsonPath("$.[0].userId").value(ADMIN.getId()));
    }

    @WithMockUser(username = "user2", password = "user2pass")
    @Test
    public void testUpdate() throws Exception {
        Vote updated = getUpdatedUser2();
        updated.setDate(TEN_O_CLOCK_DATE);
        mockMvc.perform(put(REST_URL + updated.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        assertMatch(service.get(updated.getId()), updated);

    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void testCreate() throws Exception {
        Vote created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL + "save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
        );

        Vote returned = readFromJson(action, Vote.class);
        created.setId(returned.getId());

    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void testGetInvalidId() throws Exception {
        mockMvc.perform(get(REST_URL + CRUMB_POTATO_ID))
                .andExpect(jsonPath("$.errorCode").value(404))
                .andExpect(jsonPath("$.message").value("Not found entity with id=" + CRUMB_POTATO_ID));
    }

    @WithMockUser(roles = "USER")
    @Test
    public void testGetInvalidArgument() throws Exception {
        mockMvc.perform(get(REST_URL + "f"))
                .andExpect(jsonPath("$.errorCode").value(400))
                .andExpect(jsonPath("$.message").value("The request could not be understood by the server: Failed to convert value of type 'java.lang.String' to required type 'int'; nested exception is java.lang.NumberFormatException: For input string: \"f\""));
    }

    @WithMockUser(username = "user2", password = "user2pass")
    @Test
    public void testUpdateInvalidId() throws Exception {
        Vote updated = getUpdated();
        mockMvc.perform(put(REST_URL + DishData.BFG_ID)//7
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(jsonPath("$.errorCode").value(404))
                .andExpect(jsonPath("$.message").value("Vote{userId=100001, dishId=100007, restaurantId=100004, date=" + updated.getDate() + ", id=100010} must be with id=100007"));

    }

    @WithMockUser(username = "user2", password = "user2pass")
    @Test
    public void testUpdateReVote() throws Exception {

        Vote updated = getUpdated();
        if (LocalDateTime.now().toLocalTime().isBefore(ELEVEN_O_CLOCK_DATE.toLocalTime())) {
            updated.setDate(TWELVE_O_CLOCK_DATE);
        }

        mockMvc.perform(put(REST_URL + updated.getId())//10
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(jsonPath("$.errorCode").value(400))
                .andExpect(jsonPath("$.message").value("The request could not be understood by the server: The time for re-voting is up to 11 hours"));
    }

    @WithMockUser(username = "user2", password = "user2pass")
    @Test
    public void testUpdateWithAnotherUser() throws Exception {
        Vote updated = getUpdatedUser2();
        updated.setDate(TEN_O_CLOCK_DATE);
        updated.setUserId(ADMIN_ID);
        mockMvc.perform(put(REST_URL + updated.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(jsonPath("$.errorCode").value(400))
                .andExpect(jsonPath("$.message").value("The request could not be understood by the server: The time for re-voting is up to 11 hours"));
    }
}
