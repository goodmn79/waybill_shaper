package com.goodmn.waybill_shaper.command;

import com.pengrad.telegrambot.model.Update;

public interface Command {
    void execute(Update update);
}
