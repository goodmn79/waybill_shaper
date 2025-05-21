package com.goodmn.waybill_shaper.handler.impl;

import com.goodmn.waybill_shaper.command.Command;
import com.goodmn.waybill_shaper.command.CommandExtractor;
import com.goodmn.waybill_shaper.keyboard.MainKeyboard;
import com.goodmn.waybill_shaper.service.CleanupService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateHandlerMain {
    private final Map<String, Command> commands;

    private final CommandExtractor commandExtractor;
    private final CleanupService cleanupService;
    private final MainKeyboard keyboard;

    public void handle(TelegramBot bot, Update update) {
        String text = "Error!";
        Long chatId = 0L;

        if (update.message() != null) {

        }
        if (update.callbackQuery() != null) {
            text = update.callbackQuery().data();
            log.info("Получен ответ: {}", text);
            chatId = update.callbackQuery().from().id();
        }

        Command command = commandExtractor.extract(text);

        if (command != null) {
            command.execute(update);
        } else {
            cleanupService.deleteLastMessage(chatId);

            SendResponse response = bot.execute(new SendMessage(chatId, "Error!")
                    .replyMarkup(keyboard.mainKeyboard()));

            cleanupService.saveSentMessage(response);
        }
    }

}
