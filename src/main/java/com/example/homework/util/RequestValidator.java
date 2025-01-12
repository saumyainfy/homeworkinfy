package com.example.homework.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class RequestValidator {

    private RequestValidator() {
        // Utility class: Prevent instantiation
    }

    public static void validateCustomerId(Long customerId) {
        if (customerId == null || customerId <= 0) {
            throw new IllegalArgumentException("Invalid customer ID. It must be a positive number.");
        }
    }

    public static void validateAndParseDates(String startDate, String endDate) {
        LocalDate now = LocalDate.now();
        LocalDate start;
        LocalDate end;

        try {
            end = (endDate != null) ? LocalDate.parse(endDate) : now;
            start = (startDate != null) ? LocalDate.parse(startDate) : end.minusMonths(3);

            if (start.isAfter(end)) {
                throw new IllegalArgumentException("Invalid date range: 'startDate' must be before 'endDate'.");
            }

        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Invalid date format. Use 'YYYY-MM-DD'.", ex);
        }
    }
}
