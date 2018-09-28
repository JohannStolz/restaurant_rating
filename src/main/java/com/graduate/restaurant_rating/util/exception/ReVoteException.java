package com.graduate.restaurant_rating.util.exception;

import org.springframework.lang.NonNull;

public class ReVoteException extends RuntimeException {
    public ReVoteException(@NonNull String msg) {
        super(msg);
    }
}
