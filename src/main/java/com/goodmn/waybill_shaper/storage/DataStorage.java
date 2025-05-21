package com.goodmn.waybill_shaper.storage;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.goodmn.waybill_shaper.constant.ButtonName.*;

@Component
public class DataStorage {
    private Map<String, String> data;

    @PostConstruct
    private void init() {
        data = Map.of(
                DATE, "",
                CUSTOMER, "",
                VEHICLE, "",
                MILEAGE, ""
        );
    }

    public String getData(String key) {
        return data.get(key);
    }

    public void addData(String key, String value) {
        if (data.containsKey(key)) {
            data.put(key, value);
        }
    }
}
