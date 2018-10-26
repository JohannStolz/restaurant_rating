package com.graduate.restaurant_rating.testdata;

import com.graduate.restaurant_rating.domain.Role;
import com.graduate.restaurant_rating.domain.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

import static com.graduate.restaurant_rating.domain.AbstractBaseEntity.SEQ_START;
import static com.graduate.restaurant_rating.domain.Sex.*;
import static org.assertj.core.api.Assertions.assertThat;

public class UserData {
    private static PasswordEncoder encoder = new BCryptPasswordEncoder();
    public static final int ADMIN_ID = SEQ_START;
    public static final int USER1_ID = SEQ_START + 1;
    public static final int USER2_ID = SEQ_START + 2;
    public static final User ADMIN = new User(
            ADMIN_ID
            , "admin"
            , 23
            , SEX_WHATEVER
            , "admin@rating.com"
            , true
            , encoder.encode("adminpass")
            , Role.ROLE_USER, Role.ROLE_ADMIN);
    public static final User USER1 = new User(
            USER1_ID
            , "user1"
            , 18
            , SEX_MALE
            , "user1@rating.com"
            , true
            , encoder.encode("user1pass")
            , Role.ROLE_USER);
    public static final User USER2 = new User(
            USER2_ID
            , "user2"
            , 40
            , SEX_FEMALE
            , "user2@rating.com"
            , true
            , encoder.encode("user2pass")
            , Role.ROLE_USER);

    public static User getCreated() {
        return new User(
                null
                , "newUser"
                , 66
                , SEX_MALE
                , "newUser@rating.com"
                , true
                , encoder.encode("newUserpass")
                , Role.ROLE_USER);
    }

    public static User getUpdated() {
        return new User(
                USER1_ID
                , "user1Updated"
                , 18
                , SEX_MALE
                , "user1@rating.com"
                , true
                , encoder.encode("user1pass")
                , Role.ROLE_USER);
    }

    public static List<User> getAllUsers() {
        return Arrays.asList(ADMIN, USER1, USER2);
    }

    public static <T> void assertMatchUser(T actual, T expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "role", "password").isEqualTo(expected);
    }

    public static <T> void assertMatchUsers(Iterable<T> actual, Iterable<T> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("role", "password").isEqualTo(expected);
    }
}
