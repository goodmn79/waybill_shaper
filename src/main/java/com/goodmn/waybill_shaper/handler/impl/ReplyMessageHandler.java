package com.goodmn.waybill_shaper.handler.impl;

import com.goodmn.waybill_shaper.command.Command;
import com.goodmn.waybill_shaper.handler.UpdateHandler;
import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.goodmn.waybill_shaper.constant.Cmd.ERROR_CMD;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReplyMessageHandler implements UpdateHandler {
    private final Map<String, Command> commands;

    @Override
    public boolean canHandle(Update update) {
        return update.message().replyToMessage() != null;
    }

    @Override
    public void handle(Update update) {
        log.warn("Reply message skipping");
        Command command = commands.get(ERROR_CMD);
        command.execute(update);
    }
}
