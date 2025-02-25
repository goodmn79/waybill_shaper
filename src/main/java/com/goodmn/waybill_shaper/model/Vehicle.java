package com.goodmn.waybill_shaper.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;

import static com.goodmn.waybill_shaper.constant.Constant.EMPTY_STRING;

@Entity
@Table(name = "vehicles")
@Data
@Accessors(chain = true)
public class Vehicle {
    @Id
    private long id;
    private String registrationMark;
    private String mark;
    private String type;

    public static Vehicle getDefault() {
        return new Vehicle()
                .setRegistrationMark(EMPTY_STRING)
                .setMark(EMPTY_STRING)
                .setType(EMPTY_STRING);
    }
}
