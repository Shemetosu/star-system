package com.spacestar.starsystem.converter.uses;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateConverter {

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String TIME_PATTERN = "HH:mm:ss";
    private static final String DATE_TIME_PATTERN = DATE_PATTERN + " " + TIME_PATTERN;

    public static String convertDate(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
    }
}
