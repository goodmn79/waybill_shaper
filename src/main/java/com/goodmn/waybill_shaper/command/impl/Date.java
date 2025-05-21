package com.goodmn.waybill_shaper.command.impl;

import com.goodmn.waybill_shaper.command.Command;
import com.goodmn.waybill_shaper.executor.TelegramBotExecutor;
import com.goodmn.waybill_shaper.keyboard.CalendarKeyboard;
import com.goodmn.waybill_shaper.service.CleanupService;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.goodmn.waybill_shaper.constant.Constant.SELECT_DATE;

@Component
@RequiredArgsConstructor
public class Date implements Command {
    private final TelegramBotExecutor executor;
    private final CalendarKeyboard calendarKeyboard;
    private final CleanupService cleanupService;

    @Override
    public void execute(Update update) {
        long chatId = update.callbackQuery().from().id();

        cleanupService.deleteLastMessage(chatId);

        SendResponse response = executor.sendMessage(new SendMessage(chatId, SELECT_DATE)
                .replyMarkup(calendarKeyboard.calendar()));

        cleanupService.saveSentMessage(response);
    }
}
