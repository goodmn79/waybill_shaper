package com.goodmn.waybill_shaper.command.impl;

import com.goodmn.waybill_shaper.command.Command;
import com.pengrad.telegrambot.model.Update;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.goodmn.waybill_shaper.constant.Cmd.IGNORE_CMD;

@Slf4j
@Component(IGNORE_CMD)
public class IgnoreCmd implements Command {
    @Override
    public String cmd() {
        return IGNORE_CMD;
    }

    @Override
    public void execute(Update update) {
        log.info("Do nothing");
    }
}
