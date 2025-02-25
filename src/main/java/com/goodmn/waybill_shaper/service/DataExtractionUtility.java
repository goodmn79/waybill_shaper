package com.goodmn.waybill_shaper.service;

import com.pengrad.telegrambot.model.Message;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.goodmn.waybill_shaper.extractor.Constant.*;

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
            data.add(MILEAGE + message.text());
            statusManager.removeWaitingDataStatus();
        } else {
            this.userId = message.from().id();
            log.info("Идентификатор пользователя, для получения данных о водителе: \"{}\"", userId);

            String text = message.text();
            String[] lines = text.split("\\n");

            this.data = Arrays.stream(lines)
                    .filter(s -> !s.contains("---"))
                    .map(this::format)
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
            if (text.contains(VEHICLE_TYPE)) {
                statusManager.setWaitingDataStatus();
            }
        }
    }

    public List<String> getOrderElement(String matcher) {
        log.debug("Поиск данных для извлечения...");

        List<String> orderElements = new ArrayList<>();
        for (String s : this.data) {
            if (s.contains(matcher)) {
                log.debug("Данные для извлечения успешно получены.");
                orderElements.add(s);
            }
        }
        log.debug("Данных не найдено!");
        return orderElements;
    }

    private String format(String text) {
        return text.replaceAll("[\\s\\p{Punct}]+$", EMPTY_STRING).trim();
    }
}
