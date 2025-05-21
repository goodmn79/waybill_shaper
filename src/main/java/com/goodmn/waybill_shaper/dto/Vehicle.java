package com.goodmn.waybill_shaper.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;

import static com.goodmn.waybill_shaper.constant.Constant.EMPTY_STRING;

@Entity
@Table(name = "VEHICLES", schema = "PUBLIC")
@Data
@Accessors(chain = true)
public class Vehicle {
    @Id
    @Column(name = "ID")
    private long id;
    @Column(name = "REGISTRATION_MARK")
    private String registrationMark;
    @Column(name = "MARK")
    private String mark;
    @Column(name = "TYPE")
    private String type;

    public static Vehicle getDefault() {
        return new Vehicle()
                .setRegistrationMark(EMPTY_STRING)
                .setMark(EMPTY_STRING)
                .setType(EMPTY_STRING);
    }
}
