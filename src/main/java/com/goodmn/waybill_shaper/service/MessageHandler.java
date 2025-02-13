package com.goodmn.waybill_shaper.service;

import com.pengrad.telegrambot.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.goodmn.waybill_shaper.extractor.Constant.EMPTY_STRING;
import static com.goodmn.waybill_shaper.extractor.Constant.START_COMMAND;

@Service
public class MessageHandler {

    private final Logger log = LoggerFactory.getLogger(MessageHandler.class);

    public List<String> order(Message message) {
        String text = message.text();
        String[] lines = text.split("\\n");
        return Arrays.stream(lines)
                .filter(s -> !s.contains("---"))
                .map(this::format)
                .distinct()
                .sorted()
                .toList();
    }

    public List<String> getOrderElement(Message message, String matcher) {
        log.debug("Поиск данных для извлечения...");

        List<String> orderElements = new ArrayList<>();
        for (String s : this.order(message)) {
            if (s.contains(matcher)) {
                log.debug("Данные для извлечения успешно получены.");
                orderElements.add(s);
            }
        }
        log.debug("Данных не найдено!");
        return orderElements;
    }

    public boolean isStartCommand(Message message) {
        String text = message.text();
        return text.startsWith(START_COMMAND);
    }

    private String format(String text) {
        return text.replaceAll("[\\s\\p{Punct}]+$", EMPTY_STRING).trim();
    }
}
