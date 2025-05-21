package com.goodmn.waybill_shaper.command.impl;

import com.goodmn.waybill_shaper.command.Command;
import com.goodmn.waybill_shaper.executor.TelegramBotExecutor;
import com.goodmn.waybill_shaper.keyboard.CalendarKeyboard;
import com.goodmn.waybill_shaper.cleaner.ChatCleaner;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.goodmn.waybill_shaper.constant.Cmd.SELECT_DATE_CMD;
import static com.goodmn.waybill_shaper.constant.Constant.SELECT_DATE;

@Component(SELECT_DATE_CMD)
@EqualsAndHashCode
@RequiredArgsConstructor
public class SelectDateCmd implements Command {
    private final TelegramBotExecutor executor;
    private final CalendarKeyboard calendarKeyboard;
    private final ChatCleaner chatCleaner;

    @Override
    public String cmd() {
        return SELECT_DATE_CMD;
    }

    @Override
    public void execute(Update update) {
        long chatId = update.callbackQuery().from().id();

        SendResponse response = executor.sendMessage(new SendMessage(chatId, SELECT_DATE)
                .replyMarkup(calendarKeyboard.calendar()));

        chatCleaner.deleteLastMessage(chatId);

        chatCleaner.saveSentMessage(response);
    }
}
