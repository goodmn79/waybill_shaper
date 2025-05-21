package com.goodmn.waybill_shaper.listener;

import com.goodmn.waybill_shaper.handler.UpdateHandler;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramBotUpdatesListener implements UpdatesListener {
    private final TelegramBot telegramBot;
    //    private final StatusManager statusManager;
    private final List<UpdateHandler> updateHandler;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {

        updates.forEach(update -> {
            log.info("Получено обновление: {}", update);

            updateHandler
                    .stream()
                    .filter(h -> h.canHandle(update))
                    .findFirst()
                    .ifPresent(h ->
                            h.handle(update)
                    );

//            if (!statusManager.isWaitingDataStatus()) {
//                log.info("Файл отправлен пользователю.");
//                messageHandler.deleteFile(FILE_NAME);
//            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
