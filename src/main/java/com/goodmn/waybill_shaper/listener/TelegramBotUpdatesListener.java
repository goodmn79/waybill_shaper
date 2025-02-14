package com.goodmn.waybill_shaper.listener;

import com.goodmn.waybill_shaper.service.MessageHandler;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.goodmn.waybill_shaper.extractor.Constant.FILE_NAME;

@Service
@RequiredArgsConstructor
public class TelegramBotUpdatesListener implements UpdatesListener {
    private final TelegramBot telegramBot;
    private final MessageHandler messageHandler;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            Message message = update.message();
            telegramBot.execute(messageHandler.handleMessage(message));

            messageHandler.deleteFile(FILE_NAME);
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
