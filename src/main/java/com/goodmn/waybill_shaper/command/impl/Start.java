package com.goodmn.waybill_shaper.command.impl;

import com.goodmn.waybill_shaper.command.Command;
import com.goodmn.waybill_shaper.executor.TelegramBotExecutor;
import com.goodmn.waybill_shaper.keyboard.MainKeyboard;
import com.goodmn.waybill_shaper.service.CleanupService;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.goodmn.waybill_shaper.constant.Constant.INFO;

@Component
@RequiredArgsConstructor
public class Start implements Command {
    private final TelegramBotExecutor executor;
    private final MainKeyboard mainKeyboard;
    private final CleanupService cleanupService;

    @Override
    public void execute(Update update) {
        long chatId = update.message().chat().id();

        SendResponse response = executor.sendMessage(new SendMessage(chatId, INFO)
                .replyMarkup(mainKeyboard.mainKeyboard()));

        cleanupService.deleteLastMessage(chatId);

        cleanupService.deleteInputMessage(update);

        cleanupService.saveSentMessage(response);
    }
}
