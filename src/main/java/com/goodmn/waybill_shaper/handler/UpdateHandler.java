package com.goodmn.waybill_shaper.handler;

import com.pengrad.telegrambot.model.Update;

import java.io.IOException;

public interface UpdateHandler {
    boolean canHandle(Update update);

    void handle(Update update) throws IOException;
}
