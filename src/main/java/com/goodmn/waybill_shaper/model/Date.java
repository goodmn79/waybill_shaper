package com.goodmn.waybill_shaper.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.goodmn.waybill_shaper.constant.Constant.EMPTY_STRING;

@Data
@Accessors(chain = true)
public class Date {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private LocalDate date;

    public Date(String date) {
        this.date = LocalDate.parse(date, FORMATTER);
    }

    public String numericDateFormat() {
        return null;
    }

    public String textDateFormat() {
        return null;
    }

}
