package com.graduate.restaurant_rating.web;

import com.graduate.restaurant_rating.domain.Dish;
import com.graduate.restaurant_rating.service.DishService;
import com.graduate.restaurant_rating.testdata.DishData;
import com.graduate.restaurant_rating.to.DishWithVotes;
import com.graduate.restaurant_rating.web.json.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.graduate.restaurant_rating.testdata.DishData.*;
import static com.graduate.restaurant_rating.testdata.UserData.ADMIN;
import static com.graduate.restaurant_rating.testdata.UserData.ADMIN_ID;
import static com.graduate.restaurant_rating.utils.TestUtil.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class DishRestControllerTest extends AbstractControllerTest {
    private String REST_URL = DishRestController.REST_URL + "/";
    private List<Dish> all = new ArrayList<>(DishData.getAllDishes());
    @Autowired
    private DishService service;


    @Test
    @WithMockUser(roles = "USER")
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + BELYASH_FOR_GENTS.getId()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(BELYASH_FOR_GENTS));
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + CRUMB_POTATOSHKA.getId()))
                .andExpect(status().isNoContent());
        all.remove(CRUMB_POTATOSHKA);
        assertMatch(service.getAll(), all);
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGetAll() throws Exception {
        mockMvc.perform(post(REST_URL)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(DishData.getAllDishes()));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetAllPost() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(DishData.getWithVotes()));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdate() throws Exception {
        Dish updated = getUpdated();
        mockMvc.perform(put(REST_URL + CRUMB_POTATOSHKA.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
        )
                .andExpect(status().isNoContent());
        assertMatch(service.get(CRUMB_POTATOSHKA.getId()), updated);

    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGetBetween() throws Exception {
        List<DishWithVotes> expected = DishData.getWithVotesByToday();
        mockMvc.perform(get(REST_URL + "filter")
                .param("startDate", String.valueOf(LocalDate.now()))
                .param("endDate", String.valueOf(LocalDate.now()))
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(contentJson(expected));

    }

    @Test
    @WithMockUser(roles = "USER")
    public void testFilterAll() throws Exception {
        mockMvc.perform(get(REST_URL + "filter?startDate=&endTime=")
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(contentJson(DishData.getWithVotes()));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testCreate() throws Exception {
        Dish created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL + "save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
        );
        Dish returned = readFromJson(action, Dish.class);
        created.setId(returned.getId());
        assertMatch(returned, created);
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGetInvalidId() throws Exception {
        mockMvc.perform(get(REST_URL + ADMIN_ID))
                .andExpect(jsonPath("$.errorCode").value(404))
                .andExpect(jsonPath("$.message").value("Not found entity with id=" + ADMIN_ID))
                .andDo(print());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGetInvalidArgument() throws Exception {
        mockMvc.perform(get(REST_URL + "f"))
                .andExpect(jsonPath("$.errorCode").value(400))
                .andExpect(jsonPath("$.message").value("The request could not be understood by the server: Failed to convert value of type 'java.lang.String' to required type 'int'; nested exception is java.lang.NumberFormatException: For input string: \"f\""))
                .andDo(print());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testUpdateInvalidId() throws Exception {
        mockMvc.perform(put(REST_URL + ADMIN_ID))
                .andExpect(jsonPath("$.errorCode").value(400))
                .andExpect(jsonPath("$.message").value("The request could not be understood by the server: Content type '' not supported"))
                .andDo(print());
    }
    @Test
    @WithMockUser(roles = "USER")
    public void testUnAuth() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

}