package com.graduate.restaurant_rating.util.exception;

/**
 * Created by Johann Stolz 14.08.2018
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}