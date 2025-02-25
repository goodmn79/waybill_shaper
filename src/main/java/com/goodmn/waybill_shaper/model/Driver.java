package com.goodmn.waybill_shaper.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import static com.goodmn.waybill_shaper.constant.Constant.EMPTY_STRING;
import static com.goodmn.waybill_shaper.constant.Constant.POINT;

@Entity
@Table(name = "drivers")
@Data
@Accessors(chain = true)
public class Driver {
    private final String PATTERN = "%s %s %s";

    @Id
    private long id;
    private String lastName;
    private String firstName;
    private String midlName;
    private String dl;
    private String ssn;

    public String getFullName() {
        return String.format(PATTERN, lastName, firstName, midlName);
    }

    public String getLastNameAndInitials() {
        return String.format(PATTERN, lastName, initial(firstName), initial(midlName));
    }

    private String initial(String name) {
        if (StringUtils.isBlank(name)) return EMPTY_STRING;
        String replaced = name.substring(1);
        return StringUtils.replace(name, replaced, POINT);
    }

    public static Driver getDefault() {
        return new Driver()
                .setLastName(EMPTY_STRING)
                .setFirstName(EMPTY_STRING)
                .setMidlName(EMPTY_STRING)
                .setDl(EMPTY_STRING)
                .setSsn(EMPTY_STRING);
    }
}
