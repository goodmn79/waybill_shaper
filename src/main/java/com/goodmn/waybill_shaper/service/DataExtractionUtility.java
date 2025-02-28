package com.goodmn.waybill_shaper.service;

import com.goodmn.waybill_shaper.constant.DataType;
import com.pengrad.telegrambot.model.Message;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.goodmn.waybill_shaper.constant.Constant.EMPTY_STRING;
import static com.goodmn.waybill_shaper.constant.DataType.MILEAGE;
import static com.goodmn.waybill_shaper.constant.DataType.VEHICLE_TYPE;

@Component
@Getter
@RequiredArgsConstructor
public class DataExtractionUtility {
    private final StatusManager statusManager;

    private long userId;
    private List<String> data;

    private final Logger log = LoggerFactory.getLogger(DataExtractionUtility.class);

    public void setData(Message message) {
        if (statusManager.isWaitingDataStatus()) {
            data.add(MILEAGE.getValue() + message.text());
            statusManager.removeWaitingDataStatus();
        } else {
            this.userId = message.from().id();

            String text = message.text();
            String[] lines = text.split("\\n");

            this.data = Arrays.stream(lines)
                    .filter(DataType::isCorrectDataType)
                    .map(this::format)
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
            if (this.isVehicleData(text)) {
                statusManager.setWaitingDataStatus();
            }
        }
    }

    public List<String> getOrderElement(DataType type) {
        log.debug("Поиск данных для извлечения...");

        boolean notExists = true;

        List<String> orderElements = new ArrayList<>();
        for (String s : this.data) {
            if (s.contains(type.getValue())) {
                String orderType = StringUtils.substringBefore(s, ":");
                notExists = false;
                log.debug("Данные для извлечения: '{}' успешно получены.", orderType);
                orderElements.add(s);
            }
        }
        if (notExists) log.error("Данных не найдено!");
        return orderElements;
    }

    private String format(String text) {
        return text.replaceAll("[\\s\\p{Punct}]+$", EMPTY_STRING).trim();
    }

    private boolean isVehicleData(String data) {
        return data.contains(VEHICLE_TYPE.getValue());
    }
}
