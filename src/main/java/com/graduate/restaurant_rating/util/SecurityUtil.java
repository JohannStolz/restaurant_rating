package com.graduate.restaurant_rating.util;

import com.graduate.restaurant_rating.domain.User;
import com.graduate.restaurant_rating.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import static java.util.Objects.requireNonNull;

@Component
public class SecurityUtil {
    private UserService service;

    @Autowired
    public SecurityUtil(UserService service) {
        this.service = service;
    }

    private Integer safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((!(auth instanceof AnonymousAuthenticationToken)) && auth != null) {
            String userName = auth.getName();
            User user = service.getByName(userName);
            return user.getId();
        }
        return null;
    }

    public Integer get() {
        Integer user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    public int authUserId() {
        return get();
    }
}


