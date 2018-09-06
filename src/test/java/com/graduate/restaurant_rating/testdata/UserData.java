package com.graduate.restaurant_rating.testdata;

import com.graduate.restaurant_rating.domain.Role;
import com.graduate.restaurant_rating.domain.User;

import static com.graduate.restaurant_rating.domain.AbstractBaseEntity.SEQ_START;
import static com.graduate.restaurant_rating.domain.Sex.SEX_FEMALE;
import static com.graduate.restaurant_rating.domain.Sex.SEX_MALE;
import static com.graduate.restaurant_rating.domain.Sex.SEX_WHATEVER;

/**
 * Created by Johann Stolz 05.09.2018
 */
public class UserData {
    public static final int ADMIN_ID = SEQ_START;
    public static final int USER1_ID  = SEQ_START + 1;
    public static final int USER2_ID  = SEQ_START + 2;
    public static final User ADMIN = new User(
            ADMIN_ID
            , "admin"
            , 23
            , SEX_WHATEVER
            , "admin@rating.com"
            , true
            , "adminpass"
            , Role.ROLE_ADMIN, Role.ROLE_USER);
    public static final User USER1 = new User(
            USER1_ID
            , "user1"
            , 18
            , SEX_MALE
            , "user1@rating.com"
            , true
            , "user1pass"
            , Role.ROLE_USER);
    public static final User USER2 = new User(
            USER2_ID
            , "user2"
            , 40
            , SEX_FEMALE
            , "user2@rating.com"
            , true
            , "user2pass"
            , Role.ROLE_USER);
    public static final User NEW_USER = new User(
            null
            , "newUser"
            , 66
            , SEX_MALE
            , "newUser@rating.com"
            , true
            , "newUserpass"
            , Role.ROLE_USER);

    
}
