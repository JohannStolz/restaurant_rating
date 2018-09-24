package com.graduate.restaurant_rating.web;

import com.graduate.restaurant_rating.domain.Dish;
import com.graduate.restaurant_rating.service.DishService;
import com.graduate.restaurant_rating.testdata.DishData;
import com.graduate.restaurant_rating.to.DishWithVotes;
import com.graduate.restaurant_rating.web.json.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.graduate.restaurant_rating.testdata.DishData.*;
import static com.graduate.restaurant_rating.testdata.UserData.ADMIN;
import static com.graduate.restaurant_rating.testdata.UserData.USER1;
import static com.graduate.restaurant_rating.utils.TestUtil.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DishRestControllerTest extends AbstractControllerTest {
    private String REST_URL = DishRestController.REST_URL + "/";
    private ArrayList<Dish> all = new ArrayList<>(DishData.getAllDishes());
    @Autowired
    private DishService service;


    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(DishData.getAllDishes()));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + CRUMB_POTATOSHKA.getId())
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());
        all.remove(CRUMB_POTATOSHKA);
        assertThat(service.getAll()).isEqualTo(all);
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(DishData.getAllDishes()));
    }

    @Test
    public void testGetAllPost() throws Exception {
        mockMvc.perform(post(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(DishData.getWithVotes()));
    }

    @Test
    public void testUpdate() throws Exception {
        Dish updated = getUpdated();
        mockMvc.perform(put(REST_URL + CRUMB_POTATOSHKA.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(user("user1").password("user1pass"))) /// PAY ATTENTION
                .andExpect(status().isNoContent());
        assertThat(service.get(CRUMB_POTATOSHKA.getId())).isEqualTo(updated);

    }

    @Test
    public void testGetBetween() throws Exception {
        List<DishWithVotes> expected = DishData.getWithVotesByToday();
        mockMvc.perform(get(REST_URL + "filter")
                .param("startDate", String.valueOf(LocalDate.now()))
                .param("endDate", String.valueOf(LocalDate.now()))
                .with(user("user1").password("user1pass")))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(contentJson(expected));

    }

    @Test
    public void testFilterAll() throws Exception {
        mockMvc.perform(get(REST_URL + "filter?startDate=&endTime=")
                .with(userHttpBasic(USER1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(contentJson(DishData.getWithVotes()));
    }

    @Test
    public void testCreate() throws Exception {
        Dish created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL + "save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(ADMIN)));
        Dish returned = readFromJson(action, Dish.class);
        created.setId(returned.getId());
        assertThat(returned).isEqualTo(created);
    }

}