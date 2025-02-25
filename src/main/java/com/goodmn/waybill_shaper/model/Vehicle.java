package com.goodmn.waybill_shaper.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Vehicle {
    private long id;
    private String registrationMark;
    private String mark;
    private String type;
}
