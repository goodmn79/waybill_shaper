package com.goodmn.waybill_shaper.extractor;

import com.pengrad.telegrambot.model.Message;

public interface Extractable<T> {
    T extractData(Message message);
}
