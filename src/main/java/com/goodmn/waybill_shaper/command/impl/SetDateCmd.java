package com.goodmn.waybill_shaper.command.impl;

import com.goodmn.waybill_shaper.cleaner.ChatCleaner;
import com.goodmn.waybill_shaper.command.Command;
import com.goodmn.waybill_shaper.executor.TelegramBotExecutor;
import com.goodmn.waybill_shaper.extractor.Extractor;
import com.goodmn.waybill_shaper.keyboard.MainKeyboard;
import com.goodmn.waybill_shaper.storage.DataStorage;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.goodmn.waybill_shaper.constant.ButtonName.DATE;
import static com.goodmn.waybill_shaper.constant.Cmd.SET_DATE_CMD;
import static com.goodmn.waybill_shaper.constant.Constant.INFO;

@Component(SET_DATE_CMD)
@EqualsAndHashCode
@RequiredArgsConstructor
public class SetDateCmd implements Command {
    private final TelegramBotExecutor executor;
    private final Extractor<String> extractor;
    private final DataStorage storage;
    private final MainKeyboard keyboard;
    private final ChatCleaner chatCleaner;

    @Override
    public String cmd() {
        return SET_DATE_CMD;
    }

    @Override
    public void execute(Update update) {
        long chatId = update.callbackQuery().from().id();

        String data = extractor.extract(update.callbackQuery().data());
        storage.addData(DATE, data);
        keyboard.setDate(data);
        SendResponse response = executor.sendMessage(new SendMessage(chatId, INFO)
                .replyMarkup(keyboard.mainKeyboard()));

        chatCleaner.deleteLastMessage(chatId);

        chatCleaner.saveSentMessage(response);
    }
}
