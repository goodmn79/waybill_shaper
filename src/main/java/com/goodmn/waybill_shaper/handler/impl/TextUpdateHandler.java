package com.goodmn.waybill_shaper.handler.impl;

import com.goodmn.waybill_shaper.command.Command;
import com.goodmn.waybill_shaper.command.impl.InputMileageCmd;
import com.goodmn.waybill_shaper.extractor.Extractor;
import com.goodmn.waybill_shaper.handler.UpdateHandler;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.goodmn.waybill_shaper.constant.Cmd.SET_MILEAGE_CMD;

@Slf4j
@Component
@RequiredArgsConstructor
public class TextUpdateHandler implements UpdateHandler {
    private final Extractor<Command> extractor;
    private final Map<String, Command> commands;

    @Override
    public boolean canHandle(Update update) {
        Message message = update.message();
        return message.text() != null && message.replyToMessage() == null;
    }

    @Override
    public void handle(Update update) {
        String text = update.message().text();
        Command command;

        if (InputMileageCmd.isRequestMileage()) {
            log.info("Handle message with mileage");
            command = commands.get(SET_MILEAGE_CMD);
            command.execute(update);

            InputMileageCmd.removeRequestMileage();
        } else {
            log.info("Handle input message");
            command = extractor.extract(text);
            command.execute(update);
        }
    }
}
