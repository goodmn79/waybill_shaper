package com.goodmn.waybill_shaper.listener;

import com.goodmn.waybill_shaper.service.WaybillService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.request.SendDocument;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

import static com.goodmn.waybill_shaper.extractor.Constant.FILE_NAME;
import static com.goodmn.waybill_shaper.extractor.Constant.INFO_MESSAGE;

@Service
@RequiredArgsConstructor
public class TelegramBotUpdatesListener implements UpdatesListener {
    private static final Logger log = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private final TelegramBot telegramBot;
    private final WaybillService waybillService;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            log.info("Процесс заполнения документа...");
            Message message = update.message();
            long chatId = message.chat().id();

            Object object = waybillService.handleMessage(message);

            if (object instanceof String text) {
                sendMessage(chatId, text);
            }

            if (object instanceof File file) {
                telegramBot.execute(new SendDocument(chatId, file));
                log.info("Документ отправлен получателю!");

                sendMessage(chatId, INFO_MESSAGE);

                waybillService.deleteFile(FILE_NAME);
            }

        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void sendMessage(long chatId, String text) {
        telegramBot.execute(new SendMessage(chatId, text)
                .replyMarkup(new ForceReply()));
    }

}
