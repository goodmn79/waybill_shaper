package com.goodmn.waybill_shaper.command.impl;

import com.goodmn.waybill_shaper.command.Command;
import com.goodmn.waybill_shaper.executor.TelegramBotExecutor;
import com.goodmn.waybill_shaper.keyboard.MainKeyboard;
import com.goodmn.waybill_shaper.cleaner.ChatCleaner;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import static com.goodmn.waybill_shaper.constant.Cmd.DATE_CMD;
import static com.goodmn.waybill_shaper.constant.Constant.INFO;

@Component(DATE_CMD)
@RequiredArgsConstructor
public class Date implements Command {
    private final TelegramBotExecutor executor;
    private final MainKeyboard keyboard;
    private final ChatCleaner chatCleaner;

    @Override
    public String cmd() {
        return DATE_CMD;
    }

    @Override
    public void execute(Update update) {
        long chatId = update.callbackQuery().from().id();

        String data = update.callbackQuery().data();
        String date = StringUtils.substringAfter(data, DATE_CMD).trim();
        keyboard.setDate(date);
        SendResponse response = executor.sendMessage(new SendMessage(chatId, INFO)
                .replyMarkup(keyboard.mainKeyboard()));

        chatCleaner.deleteLastMessage(chatId);

        chatCleaner.saveSentMessage(response);
    }
}
