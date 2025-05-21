package com.goodmn.waybill_shaper.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import static com.goodmn.waybill_shaper.constant.Constant.EMPTY_STRING;

@Data
@Accessors(chain = true)
public class Mileage {
    private String mileage;

    public static Mileage getDefault() {
        return new Mileage()
                .setMileage(EMPTY_STRING);
    }
}
