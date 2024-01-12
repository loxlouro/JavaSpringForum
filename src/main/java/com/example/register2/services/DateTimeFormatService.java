package com.example.register2.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatService {
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    public static String toFormat(LocalDateTime localDateTime){
        return localDateTime.format(dateTimeFormatter);
    }

}
