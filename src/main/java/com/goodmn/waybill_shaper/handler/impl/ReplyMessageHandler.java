package com.goodmn.waybill_shaper.handler.impl;

import com.goodmn.waybill_shaper.command.impl.Mileage;
import com.goodmn.waybill_shaper.executor.TelegramBotExecutor;
import com.goodmn.waybill_shaper.handler.UpdateHandler;
import com.goodmn.waybill_shaper.keyboard.MainKeyboard;
import com.goodmn.waybill_shaper.service.CleanupService;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReplyMessageHandler implements UpdateHandler {
    private final TelegramBotExecutor executor;
    private final MainKeyboard keyboard;
    private final CleanupService cleanupService;

    @Override
    public boolean canHandle(Update update) {
        return update.message().replyToMessage() != null;
    }

    @Override
    public void handle(Update update) {
        long chatId = update.message().replyToMessage().chat().id();

        cleanupService.deleteLastMessage(chatId);
        cleanupService.deleteInputMessage(update);

        if (Mileage.isRequestMileage()) {
            String mileage = update.message().text();
            keyboard.setMileage(mileage);
            SendResponse response = executor.sendMessage(new SendMessage(chatId, "Продолжим:")
                    .replyMarkup(keyboard.mainKeyboard()));

            Mileage.removeRequestMileage();

            cleanupService.saveSentMessage(response);
        }
    }
}
