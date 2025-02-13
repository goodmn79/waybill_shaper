package com.goodmn.waybill_shaper.extractor;

import com.goodmn.waybill_shaper.model.Date;
import com.goodmn.waybill_shaper.service.MessageHandler;
import com.pengrad.telegrambot.model.Message;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static com.goodmn.waybill_shaper.extractor.Constant.*;

@Component
@RequiredArgsConstructor
public class DateExtractor implements Extractable<Date> {
    private final MessageHandler messageHandler;

    private final Logger log = LoggerFactory.getLogger(DateExtractor.class);

    @Override
    public Date extractData(Message message) {
        log.debug("Извлечение данных о дате заказа...");

        List<String> orderDate = messageHandler.getOrderElement(message, DATE);

        String numericDateFormat;
        String textDateFormat;
        if (orderDate.size() > 1) {
            numericDateFormat = getNumericDateFormat(orderDate.get(0));
            textDateFormat = getTextDateFormat(orderDate.get(1));
        } else if (orderDate.size() == 1) {
            numericDateFormat = getNumericDateFormat(orderDate.get(0));
            textDateFormat = getTextDateFormat(orderDate.get(0));
        } else {
            numericDateFormat = getNumericDateFormat(EMPTY_STRING);
            textDateFormat = getTextDateFormat(EMPTY_STRING);
        }

        log.debug("СОКРАЩЕННЫЙ ФОРМАТ ДАТЫ: '{}', ПОЛНЫЙ ФОРМАТ ДАТЫ: '{}'", numericDateFormat, textDateFormat);
        return new Date()
                .setNumericDateFormat(numericDateFormat)
                .setTextDateFormat(textDateFormat);
    }

    private String extractDate(String orderData) {
        log.debug("СТРОКА С ДАННЫМИ О ДАТЕ ЗАКАЗА: '{}'.", orderData);

        return StringUtils.substringAfter(orderData, DATE);
    }

    private LocalDate getLocalDate(String orderData) {
        String date = extractDate(orderData);
        if (StringUtils.isBlank(date)) {
            log.debug("Данные о дате заказа не получены! Будет установлена текущая дата!");
            return LocalDate.now();
        }
        log.debug("Данные о дате заказа получены успешно.");
        return LocalDate.parse(date, NUMERIC_DATE_FORMAT);
    }

    private String getNumericDateFormat(String orderData) {
        LocalDate localDate = getLocalDate(orderData);
        return localDate.format(NUMERIC_DATE_FORMAT);
    }

    private String getTextDateFormat(String orderData) {
        LocalDate localDate = getLocalDate(orderData);
        return localDate.format(TEXT_DATE_FORMAT);
    }
}
