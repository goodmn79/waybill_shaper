package com.goodmn.waybill_shaper.service;

import com.goodmn.waybill_shaper.writer.Writeable;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.AbstractSendRequest;
import com.pengrad.telegrambot.request.SendDocument;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.goodmn.waybill_shaper.constant.Constant.*;

@Service
@RequiredArgsConstructor
public class MessageHandler {
    private final Workbook waybillTemplate;
    private final DataExtractionUtility extractionUtility;
    private final StatusManager statusManager;

    private final Writeable writeable;

    private final Logger log = LoggerFactory.getLogger(MessageHandler.class);

    public AbstractSendRequest<? extends AbstractSendRequest<?>> handleMessage(Message message) {
        extractionUtility.setData(message);

        if (statusManager.isWaitingDataStatus()) {
            return mileageDataRequest(message);
        } else {
            return this.sendWaybill(message).caption(caption());
        }
    }

    private SendMessage mileageDataRequest(Message message) {
        long chatId = message.chat().id();
        return new SendMessage(chatId, MILEAGE_INFO)
                .replyMarkup(
                        new ReplyKeyboardMarkup(button())
                                .oneTimeKeyboard(true)
                                .resizeKeyboard(true)
                );
    }

    private SendDocument sendWaybill(Message message) {
        long chatId = message.chat().id();
        writeable.writeData(waybillTemplate);
        return new SendDocument(chatId, this.saveToFile(waybillTemplate));
    }

    @SneakyThrows
    public void deleteFile(String fileName) {
        Path path = Path.of(fileName);

        if (Files.exists(path)) {
            Files.delete(path);
            log.info("Файл был успешно удалён.");
        }
    }

    private File saveToFile(Workbook workbook) {
        File file = new File(FILE_NAME);

        try (FileOutputStream out = new FileOutputStream(file)) {
            workbook.write(out);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return file;
    }

    private String caption() {
        if (statusManager.noDriverData()) {
            statusManager.removeAbsenceDriverDataFlag();
            return NO_DRIVER_DATA;
        }
        return EMPTY_STRING;
    }

    private KeyboardButton button() {
        return new KeyboardButton(SKIP);
    }
}
