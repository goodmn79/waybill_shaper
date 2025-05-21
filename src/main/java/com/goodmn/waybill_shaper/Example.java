package com.goodmn.waybill_shaper;

import com.pengrad.telegrambot.model.Update;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Slf4j
public class Example {

    public static void info(Update update) {
        if (update.message() != null) {
            log.info("Сообщение: {}",update.message().text());

            if (update.message().replyToMessage() != null) {
                log.info("Ответное сообщение: {}",update.message().text());
            }
        }

        if (update.callbackQuery() != null) {
            log.info("Ответ на запрос: {}",update.message().text());
        }
    }

    public static void main(String[] args) {
        String[] text = {"Hello", "World"};

        for (String s : text) {
//            System.out.println(StringUtils.substringBefore(s, "_"));
        }

        String dateStr = "2025-05-21";
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
