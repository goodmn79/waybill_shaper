package com.goodmn.waybill_shaper.command.impl;

import com.goodmn.waybill_shaper.cleaner.ChatCleaner;
import com.goodmn.waybill_shaper.command.Command;
import com.goodmn.waybill_shaper.executor.TelegramBotExecutor;
import com.goodmn.waybill_shaper.keyboard.MainKeyboard;
import com.goodmn.waybill_shaper.storage.DataStorage;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.goodmn.waybill_shaper.constant.ButtonName.MILEAGE;
import static com.goodmn.waybill_shaper.constant.Cmd.RESET_CMD;
import static com.goodmn.waybill_shaper.constant.Constant.EMPTY_STRING;
import static com.goodmn.waybill_shaper.constant.Constant.INFO;

@Component(RESET_CMD)
@RequiredArgsConstructor
public class ResetMileageCmd implements Command {
    private final DataStorage dataStorage;
    private final TelegramBotExecutor executor;
    private final MainKeyboard keyboard;
    private final ChatCleaner chatCleaner;

    @Override
    public String cmd() {
        return RESET_CMD;
    }

    @Override
    public void execute(Update update) {
        long chatId = update.callbackQuery().from().id();

        dataStorage.addData(MILEAGE, EMPTY_STRING);

        keyboard.setMileage(MILEAGE);

        SendResponse response = executor.sendMessage(new SendMessage(chatId, INFO)
                .replyMarkup(keyboard.mainKeyboard()));

        chatCleaner.deleteLastMessage(chatId);

        chatCleaner.deleteInputMessage(update);

        chatCleaner.saveSentMessage(response);
    }
}
