package com.goodmn.waybill_shaper.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import static com.goodmn.waybill_shaper.constant.Constant.EMPTY_STRING;

@Data
@Accessors(chain = true)
public class Number {
    private String waybillNumber;
    private String couponNumber;

    public static Number getDefault() {
        return new Number()
                .setWaybillNumber(EMPTY_STRING)
                .setCouponNumber(EMPTY_STRING);
    }
}
