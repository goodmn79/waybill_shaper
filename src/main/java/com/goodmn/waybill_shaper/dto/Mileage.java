package com.goodmn.waybill_shaper.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Mileage {
    private String mileage;
}
