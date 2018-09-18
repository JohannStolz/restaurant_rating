package com.graduate.restaurant_rating.service;

import com.graduate.restaurant_rating.domain.Vote;
import com.graduate.restaurant_rating.testdata.VoteData;
import com.graduate.restaurant_rating.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.graduate.restaurant_rating.testdata.VoteData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VoteServiceTest extends AbstractServiceTest {
    @Autowired
    private VoteService service;
    private ArrayList<Vote> all = new ArrayList<>(getAllVotes());

    @Test
    public void create() {
        Vote newVote = getCreated();
        service.create(newVote);
        all.add(newVote);
        assertThat(service.getAll()).isEqualTo(all);
    }

    @Test
    public void update() {
        Vote updated = getUpdated();
        service.update(updated, USER1_VOTE_ID);
        assertThat(service.get(USER1_VOTE_ID)).isEqualTo(updated);
    }

    @Test
    public void delete() {
        all.remove(ADMIN_VOTE);
        service.delete(ADMIN_VOTE_ID);
        assertThat(all).isEqualTo(service.getAll());
    }

    @Test
    public void get() {
        Vote actual = service.get(USER2_VOTE_ID);
        assertThat(actual).isEqualTo(USER2_VOTE);
    }

    @Test
    public void getAll() {
        List<Vote> actual = service.getAll();
        List<Vote> expected = all;
        assertThat(actual).isEqualTo(expected);
    }


    @Test
    public void getNotFound() throws Exception {
        int id = 1;
        assertThrows(NotFoundException.class, () ->
                service.get(id));
    }

    @Test
    public void updateNotFound() {
        int id = 0;
        Vote updated = getCreated();
        NotFoundException e = assertThrows(NotFoundException.class, () -> service.update(updated, id));
        assertEquals(e.getMessage(), "Not found entity with id=" + id);
    }

    @Test
    public void deleteNotFound() {
        Vote deleted = getCreated();
        deleted.setId(1);
        EmptyResultDataAccessException e = assertThrows(EmptyResultDataAccessException.class, () -> service.delete(deleted.getId()));
        assertEquals(e.getMessage(), "No " + Vote.class + " entity with id " + deleted.getId() + " exists!");
    }
    

}
