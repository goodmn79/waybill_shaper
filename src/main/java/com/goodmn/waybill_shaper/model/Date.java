package com.goodmn.waybill_shaper.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Date {
    private String numericDateFormat;
    private String textDateFormat;
}
