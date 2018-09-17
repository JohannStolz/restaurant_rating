package com.graduate.restaurant_rating.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    private final AuthenticationFailure authenticationFailureHandler;

    @Autowired
    public WebSecurityConfig(DataSource dataSource, AuthenticationFailure authenticationFailureHandler) {
        this.dataSource = dataSource;
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/error").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .successHandler(
                        (request, response, authentication) -> response
                                .setStatus(HttpStatus.NO_CONTENT.value()))
                .successForwardUrl("/profile/dishes")
                .failureHandler(
                        (authenticationFailureHandler))
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(
                        (request, response, authentication) -> {
                            response.setStatus(HttpStatus.NO_CONTENT.value());
                        });


    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select name, password, enabled from users where name=?")
                .authoritiesByUsernameQuery("select u.id, ur.roles from users u inner join user_roles ur on u.id = ur.user_id where u.name=?");

    }

    @Bean
    public Jackson2JsonObjectMapper jackson2JsonObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        return new Jackson2JsonObjectMapper(mapper);
    }

}
