package com.goodmn.waybill_shaper.command.impl;

import com.goodmn.waybill_shaper.command.Command;
import com.goodmn.waybill_shaper.executor.TelegramBotExecutor;
import com.goodmn.waybill_shaper.keyboard.MainKeyboard;
import com.goodmn.waybill_shaper.service.CleanupService;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import static com.goodmn.waybill_shaper.constant.Constant.INFO;

@Component
@RequiredArgsConstructor
public class Select implements Command {
    private final TelegramBotExecutor executor;
    private final MainKeyboard keyboard;
    private final CleanupService cleanupService;

    @Override
    public void execute(Update update) {
        long chatId = update.callbackQuery().from().id();

        String data = update.callbackQuery().data();
        String date = StringUtils.substringAfter(data, "_");
        keyboard.setDate(date);
        SendResponse response = executor.sendMessage(new SendMessage(chatId, INFO)
                .replyMarkup(keyboard.mainKeyboard()));

        cleanupService.deleteLastMessage(chatId);

        cleanupService.saveSentMessage(response);
    }
}
