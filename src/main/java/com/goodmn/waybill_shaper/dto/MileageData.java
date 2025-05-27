package com.goodmn.waybill_shaper.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MileageData {
    private String mileage;
}
