package com.goodmn.waybill_shaper;

import com.pengrad.telegrambot.model.Update;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class Example {

    public static void info(Update update) {
        if (update.message() != null) {
            log.info("Сообщение: {}", update.message().text());

            if (update.message().replyToMessage() != null) {
                log.info("Ответное сообщение: {}", update.message().text());
            }
        }

        if (update.callbackQuery() != null) {
            log.info("Ответ на запрос: {}", update.message().text());
        }
    }

    public static void main(String[] args) {
        String text = "abcdefgh";
        System.out.println(StringUtils.substringBefore(text, "|"));
//        System.out.println(StringUtils.substringAfter(text, "|"));
    }
}
