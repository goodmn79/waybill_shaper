package com.goodmn.waybill_shaper.handler.impl;

import com.goodmn.waybill_shaper.command.Command;
import com.goodmn.waybill_shaper.extractor.Extractor;
import com.goodmn.waybill_shaper.handler.UpdateHandler;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TextUpdateHandler implements UpdateHandler {
    private final Extractor<Command> extractor;

    @Override
    public boolean canHandle(Update update) {
        Message message = update.message();
        return message.text() != null && message.replyToMessage() == null;
    }

    @Override
    public void handle(Update update) {
        String text = update.message().text();

        Command command = extractor.extract(text);

        command.execute(update);
    }
}
