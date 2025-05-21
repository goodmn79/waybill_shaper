package com.goodmn.waybill_shaper.handler.impl;

import com.goodmn.waybill_shaper.command.Command;
import com.goodmn.waybill_shaper.command.CommandExtractor;
import com.goodmn.waybill_shaper.handler.UpdateHandler;
import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CallbackQueryHandler implements UpdateHandler {
    private final CommandExtractor commandExtractor;

    @Override
    public boolean canHandle(Update update) {
        return update.callbackQuery() != null;
    }

    @Override
    public void handle(Update update) {
        long chatId = update.callbackQuery().from().id();
        String text = update.callbackQuery().data();
        Command command = commandExtractor.extract(text);
        command.execute(update);
    }
}
