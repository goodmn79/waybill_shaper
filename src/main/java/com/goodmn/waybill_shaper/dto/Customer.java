package com.goodmn.waybill_shaper.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import static com.goodmn.waybill_shaper.constant.Constant.EMPTY_STRING;

@Data
@Accessors(chain = true)
public class Customer {
    private String customer;

    public static Customer getDefault() {
        return new Customer()
                .setCustomer(EMPTY_STRING);
    }
}
