package com.goodmn.waybill_shaper.dto;

import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Accessors(chain = true)
public class DateData {
    public static final DateTimeFormatter NUMERIC_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy", new Locale("RU"));
    public static final DateTimeFormatter TEXT_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("RU"));

    private final LocalDate date;

    public DateData(String date) {
        this.date = LocalDate.parse(date, NUMERIC_DATE_FORMATTER);
    }

    public String numericDateFormat() {
        return date.format(NUMERIC_DATE_FORMATTER);
    }

    public String textDateFormat() {
        return date.format(TEXT_DATE_FORMATTER);
    }

}
