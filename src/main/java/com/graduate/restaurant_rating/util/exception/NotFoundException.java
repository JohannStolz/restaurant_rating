package com.graduate.restaurant_rating.util.exception;


public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}