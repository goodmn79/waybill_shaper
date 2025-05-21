package com.goodmn.waybill_shaper.command.impl;

import com.goodmn.waybill_shaper.command.Command;
import com.goodmn.waybill_shaper.executor.TelegramBotExecutor;
import com.goodmn.waybill_shaper.keyboard.CalendarKeyboard;
import com.goodmn.waybill_shaper.service.CleanupService;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import static com.goodmn.waybill_shaper.constant.Constant.SELECT_DATE;

@Component
@RequiredArgsConstructor
public class Nav implements Command {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy M");

    private final TelegramBotExecutor executor;
    private final CalendarKeyboard keyboard;
    private final CleanupService cleanupService;

    @Override
    public void execute(Update update) {
        long chatId = update.callbackQuery().from().id();

        String text = update.callbackQuery().data();
        String date = StringUtils.substringAfter(text, "_");
        YearMonth yearMonth = YearMonth.parse(date, formatter);

        keyboard.setYearMonth(yearMonth);

        cleanupService.deleteLastMessage(chatId);

        SendResponse response = executor.sendMessage(new SendMessage(chatId, SELECT_DATE)
                .replyMarkup(keyboard.calendar()));

        cleanupService.saveSentMessage(response);
    }
}
