package com.goodmn.waybill_shaper.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Table(name = "drivers")
@Data
@Accessors(chain = true)
public class Driver {
    @Id
    private long id;
    private String lastName;
    private String firstName;
    private String midlName;
    private String dl;
    private String ssn;

    public String getFullName() {
        return lastName + " " + firstName + " " + midlName;
    }
}
