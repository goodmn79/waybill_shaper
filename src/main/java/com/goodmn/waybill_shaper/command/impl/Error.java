package com.goodmn.waybill_shaper.command.impl;

import com.goodmn.waybill_shaper.command.Command;
import com.goodmn.waybill_shaper.executor.TelegramBotExecutor;
import com.goodmn.waybill_shaper.keyboard.MainKeyboard;
import com.goodmn.waybill_shaper.cleaner.ChatCleaner;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.goodmn.waybill_shaper.constant.Cmd.ERROR_CMD;
import static com.goodmn.waybill_shaper.constant.Constant.INFO;

@Slf4j
@Component(ERROR_CMD)
@RequiredArgsConstructor
public class Error implements Command {
    private final TelegramBotExecutor executor;
    private final MainKeyboard keyboard;
    private final ChatCleaner chatCleaner;

    @Override
    public String cmd() {
        return ERROR_CMD;
    }

    @Override
    public void execute(Update update) {
        long chatId = update.callbackQuery() != null ? update.callbackQuery().from().id() : update.message().chat().id();

        SendResponse response = executor.sendMessage(new SendMessage(chatId, INFO)
                .replyMarkup(keyboard.mainKeyboard()));

        chatCleaner.deleteLastMessage(chatId);

        chatCleaner.deleteInputMessage(update);

        chatCleaner.saveSentMessage(response);
    }
}
