package com.graduate.restaurant_rating.util;

import com.graduate.restaurant_rating.domain.AbstractBaseEntity;
import com.graduate.restaurant_rating.util.exception.WrongIdException;

import java.util.Objects;

/**
 * Created by Johann Stolz 04.09.2018
 */
public class ValidationUtil {
    private ValidationUtil() {
    }

    public static void checkForMatchId(AbstractBaseEntity entity, int id) {
        Objects.requireNonNull(entity);
        if (!entity.getId().equals(id)) {
            throw new WrongIdException("id not equals");
        }
    }
}
