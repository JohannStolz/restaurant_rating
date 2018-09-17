package com.graduate.restaurant_rating.util;

import com.graduate.restaurant_rating.domain.AbstractBaseEntity;
import com.graduate.restaurant_rating.util.exception.NotFoundException;
import com.graduate.restaurant_rating.util.exception.WrongIdException;

import java.util.Objects;

/**
 * Created by Johann Stolz 04.09.2018
 */
public class ValidationUtil {
    private ValidationUtil() {
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "id=" + id);
    }


    public static void checkForMatchId(AbstractBaseEntity entity, int id) {
        Objects.requireNonNull(entity);
        if (!entity.getId().equals(id)) {
            throw new WrongIdException("id not equals");
        }
    }
    public static void checkDeleteSuccess(boolean delete, int id) {
        if (!delete) {
            throw new NotFoundException("Not found entity with id=" + id);
        }
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }
    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }
}
