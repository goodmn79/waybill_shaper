package com.goodmn.waybill_shaper.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Data
@Accessors(chain = true)
public class Vehicle {
    @Id
    private long id;
    private String registrationMark;
    private String mark;
    private String type;
}
