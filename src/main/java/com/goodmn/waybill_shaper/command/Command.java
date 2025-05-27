package com.goodmn.waybill_shaper.command;

import com.pengrad.telegrambot.model.Update;

import java.io.IOException;

public interface Command {
    String cmd();

    void execute(Update update);
}
