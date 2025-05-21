package com.goodmn.waybill_shaper.executor;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.pengrad.telegrambot.request.SendDocument;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelegramBotExecutor {
    private final TelegramBot bot;

    public SendResponse sendMessage(SendMessage message) {
        return bot.execute(message);
    }

    public SendResponse sendDocument(SendDocument document) {
        return bot.execute(document);
    }

    public void deleteLastMessage(DeleteMessage message) {
        bot.execute(message);
        log.info("Message deleted");
    }
}
