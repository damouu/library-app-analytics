package com.example.demo.enums;

import com.example.demo.dto.DateRange;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public enum AnalyticsPeriod {
    CURRENT_WEEK, LAST_WEEK, LAST_MONTH;

    public DateRange resolve() {
        return switch (this) {
            case LAST_WEEK -> {
                LocalDate startDate = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).minusWeeks(1);
                yield new DateRange(startDate, startDate.plusDays(6));
            }
            case LAST_MONTH -> {
                LocalDate lastMonth = LocalDate.now().minusMonths(1);
                yield new DateRange(lastMonth.withDayOfMonth(1), lastMonth.withDayOfMonth(lastMonth.lengthOfMonth()));
            }
            case CURRENT_WEEK -> {
                LocalDate startDate = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                yield new DateRange(startDate, startDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)));
            }
        };
    }
}