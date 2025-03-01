package com.goodmn.waybill_shaper.model;

import lombok.Data;
import lombok.experimental.Accessors;

import static com.goodmn.waybill_shaper.constant.Constant.EMPTY_STRING;

@Data
@Accessors(chain = true)
public class Date {
    private String numericDateFormat;
    private String textDateFormat;

    public static Date getDefault() {
        return new Date()
                .setNumericDateFormat(EMPTY_STRING)
                .setTextDateFormat(EMPTY_STRING);
    }
}
