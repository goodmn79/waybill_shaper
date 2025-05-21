package com.goodmn.waybill_shaper.handler.impl;

import com.goodmn.waybill_shaper.command.impl.MileageCmd;
import com.goodmn.waybill_shaper.executor.TelegramBotExecutor;
import com.goodmn.waybill_shaper.handler.UpdateHandler;
import com.goodmn.waybill_shaper.keyboard.MainKeyboard;
import com.goodmn.waybill_shaper.cleaner.ChatCleaner;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.goodmn.waybill_shaper.constant.Constant.INFO;

@Component
@RequiredArgsConstructor
public class ReplyMessageHandler implements UpdateHandler {
    private final TelegramBotExecutor executor;
    private final MainKeyboard keyboard;
    private final ChatCleaner chatCleaner;

    @Override
    public boolean canHandle(Update update) {
        return update.message().replyToMessage() != null;
    }

    @Override
    public void handle(Update update) {
        long chatId = update.message().replyToMessage().chat().id();

        chatCleaner.deleteLastMessage(chatId);
        chatCleaner.deleteInputMessage(update);

        if (MileageCmd.isRequestMileage()) {
            String mileage = update.message().text();
            keyboard.setMileage(mileage);
            SendResponse response = executor.sendMessage(new SendMessage(chatId, INFO)
                    .replyMarkup(keyboard.mainKeyboard()));

            MileageCmd.removeRequestMileage();

            chatCleaner.saveSentMessage(response);
        }
    }
}
