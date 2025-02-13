package com.goodmn.waybill_shaper.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Table(name = "vehicles")
@Data
@Accessors(chain = true)
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String registrationMark;
    private String mark;
    private String type;
}
