package com.goodmn.waybill_shaper.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import static com.goodmn.waybill_shaper.constant.Constant.EMPTY_STRING;
import static com.goodmn.waybill_shaper.constant.Constant.TIME_FORMAT;

@Data
@Accessors(chain = true)
public class Time {
    private String departureTime;
    private String startBreak;
    private String endBreak;
    private String arrivalTime;

    public boolean containsBreak() {
        return StringUtils.isNotEmpty(startBreak) && StringUtils.isNotEmpty(endBreak);
    }

    public String getStartShiftTime() {
        LocalTime startTime = getLocalTime(departureTime);
        if (startTime == null) {
            return EMPTY_STRING;
        }
        return getByTimeDifference(startTime).format(TIME_FORMAT);
    }

    public String getEndShiftTime() {
        LocalTime endTime = getLocalTime(arrivalTime);
        if (endTime == null) {
            return EMPTY_STRING;
        }
        return getByTimeDifference(endTime).format(TIME_FORMAT);
    }

    public static Time getDefault() {
        return new Time()
                .setDepartureTime(EMPTY_STRING)
                .setStartBreak(EMPTY_STRING)
                .setEndBreak(EMPTY_STRING)
                .setArrivalTime(EMPTY_STRING);
    }

    private LocalTime getLocalTime(String time) {
        try {
            return LocalTime.parse(time, TIME_FORMAT);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private LocalTime getByTimeDifference(LocalTime time) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        String methodName = stackTraceElements[2].getMethodName();

        if (methodName.contains("Start")) {
            return time.minusMinutes(10);
        } else if (methodName.contains("End")) {
            return time.plusMinutes(10);
        }
        return time;
    }
}
