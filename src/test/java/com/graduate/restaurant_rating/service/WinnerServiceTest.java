package com.graduate.restaurant_rating.service;

import com.graduate.restaurant_rating.to.RestaurantIdWithCountOfVotes;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static com.graduate.restaurant_rating.utils.TestUtil.assertMatch;

@Transactional
public class WinnerServiceTest extends AbstractServiceTest {
    @Autowired
    private WinnerService service;


    @Test
    public void get() {
        List<RestaurantIdWithCountOfVotes> actual = service.findRestaurantWithVotes();
        List<RestaurantIdWithCountOfVotes> expected = Arrays.asList(
                new RestaurantIdWithCountOfVotes(100004, 2L),
                new RestaurantIdWithCountOfVotes(100005, 1L),
                new RestaurantIdWithCountOfVotes(100003, 1L));
        assertMatch(actual, expected);
    }


}
