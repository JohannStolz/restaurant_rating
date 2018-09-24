package com.graduate.restaurant_rating.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Johann Stolz 14.08.2018
 */
public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
