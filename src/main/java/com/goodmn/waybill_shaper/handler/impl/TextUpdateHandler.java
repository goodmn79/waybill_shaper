package com.goodmn.waybill_shaper.handler.impl;

import com.goodmn.waybill_shaper.command.Command;
import com.goodmn.waybill_shaper.command.CommandExtractor;
import com.goodmn.waybill_shaper.handler.UpdateHandler;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class TextUpdateHandler implements UpdateHandler {
    private final CommandExtractor commandExtractor;

    @Override
    public boolean canHandle(Update update) {
        Message message = update.message();
        return message.text() != null && message.replyToMessage() == null;
    }

    @Override
    public void handle(Update update) {
        long chatId = update.message().chat().id();
        String text = update.message().text();

        Command command = commandExtractor.extract(text);

        command.execute(update);
    }
}
