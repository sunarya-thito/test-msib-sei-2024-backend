package io.github.sunaryathito.testmsibsei2024.service;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class AbstractService {

    @Value("${locale.format.date}")
    private String dateFormat;

    @Value("${locale.format.datetime}")
    private String dateTimeFormat;

    protected String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(dateFormat));
    }

    protected String formatDateTime(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern(dateTimeFormat));
    }

    protected LocalDate parseDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(dateFormat));
    }

    protected LocalDateTime parseDateTime(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(dateTimeFormat));
    }
}
