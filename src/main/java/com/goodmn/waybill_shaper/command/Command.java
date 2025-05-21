package com.goodmn.waybill_shaper.command;

import com.pengrad.telegrambot.model.Update;

public interface Command {
    String cmd();

    void execute(Update update);
}
