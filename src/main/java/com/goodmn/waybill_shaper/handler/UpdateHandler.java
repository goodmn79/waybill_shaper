package com.goodmn.waybill_shaper.handler;

import com.pengrad.telegrambot.model.Update;

public interface UpdateHandler {
    boolean canHandle(Update update);

    void handle(Update update);
}
