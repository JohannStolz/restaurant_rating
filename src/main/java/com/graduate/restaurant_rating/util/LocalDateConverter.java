package com.graduate.restaurant_rating.util;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Locale;

@Component
public class LocalDateConverter implements Formatter<LocalDate> {
    @Override
    public LocalDate parse(String s, Locale locale) throws ParseException {
        if (s.isEmpty() || s == null) {
            return null;
        } else {
            return LocalDate.parse(s);
        }
    }

    @Override
    public String print(LocalDate date, Locale locale) {

        if (date == null) {
            return null;
        } else {
            return date.toString();
        }

    }
}
