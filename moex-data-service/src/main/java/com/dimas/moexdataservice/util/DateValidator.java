package com.dimas.moexdataservice.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
@RequiredArgsConstructor
public class DateValidator {
    private final DateTimeFormatter dateTimeFormatter;

    public Boolean isValid(String date) {
        if (date == null || date.isEmpty()) {
            return false;
        }

        try {
            var parsedDate = LocalDate.parse(date, dateTimeFormatter);
            var startOfWeek = LocalDate.now().with(DayOfWeek.MONDAY).minusDays(1);
            var endOfWeek = LocalDate.now().with(DayOfWeek.SUNDAY).plusDays(1);

            return parsedDate.isAfter(startOfWeek) && parsedDate.isBefore(endOfWeek);

        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
