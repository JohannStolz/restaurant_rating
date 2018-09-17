package com.graduate.restaurant_rating;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class RestaurantRatingApplication {

    public static void main(String[] args) {

        ApplicationContext applicationContext =
                SpringApplication.run(RestaurantRatingApplication.class, args);
        // Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);

    }
}
