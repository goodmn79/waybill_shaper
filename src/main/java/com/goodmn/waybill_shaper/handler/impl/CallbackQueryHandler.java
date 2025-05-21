package com.goodmn.waybill_shaper.handler.impl;

import com.goodmn.waybill_shaper.command.Command;
import com.goodmn.waybill_shaper.extractor.Extractor;
import com.goodmn.waybill_shaper.handler.UpdateHandler;
import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CallbackQueryHandler implements UpdateHandler {
    private final Extractor<Command> extractor;

    @Override
    public boolean canHandle(Update update) {
        return update.callbackQuery() != null;
    }

    @Override
    public void handle(Update update) {
        String text = update.callbackQuery().data();
        Command command = extractor.extract(text);
        command.execute(update);
    }
}
