package com.goodmn.waybill_shaper.listener;

import com.goodmn.waybill_shaper.service.MessageHandler;
import com.goodmn.waybill_shaper.service.StatusManager;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.goodmn.waybill_shaper.constant.Constant.FILE_NAME;

@Service
@RequiredArgsConstructor
public class TelegramBotUpdatesListener implements UpdatesListener {
    private final TelegramBot telegramBot;
    private final MessageHandler messageHandler;
    private final StatusManager statusManager;

    Logger log = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        log.info("Процесс прослушивания обновлений...");

        updates.forEach(update -> {
            Message message = update.message();
            telegramBot.execute(messageHandler.handleMessage(message));

            if (!statusManager.isWaitingDataStatus()) {
                log.info("Файл отправлен пользователю.");
                messageHandler.deleteFile(FILE_NAME);
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
