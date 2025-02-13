package com.goodmn.waybill_shaper.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Number {
    private String waybillNumber;
    private String couponNumber;
}
