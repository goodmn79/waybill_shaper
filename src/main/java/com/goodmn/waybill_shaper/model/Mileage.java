package com.goodmn.waybill_shaper.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

@Data
@Accessors(chain = true)
public class Mileage {
    private String mileage;

    public static boolean isMileage(String input) {
        return StringUtils.isNumeric(input);
    }
}
