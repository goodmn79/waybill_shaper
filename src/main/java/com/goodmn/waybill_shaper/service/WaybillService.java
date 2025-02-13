package com.goodmn.waybill_shaper.service;

import com.goodmn.waybill_shaper.component.Writable;
import com.pengrad.telegrambot.model.Message;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

import static com.goodmn.waybill_shaper.extractor.Constant.*;
import static com.goodmn.waybill_shaper.model.Mileage.isMileage;

@Service
@RequiredArgsConstructor
public class WaybillService {
    private final Workbook waybillTemplate;
    private final MessageHandler messageHandler;

    private final Writable writer;
    private final Set<Writable> writable;

    private final Logger log = LoggerFactory.getLogger(WaybillService.class);

    public Object handleMessage(Message message) {
        String replyToMessage = getReplyToMessage(message);
        log.debug("ВХОДЯЩЕЕ СООБЩЕНИЕ СОДЕРЖИТ ОТВЕТ НА СООБЩЕНИЕ: \n'{}'", replyToMessage);

        if (messageHandler.isStartCommand(message)) {
            return INFO_MESSAGE;
        }
        if (replyToMessage.equals(INFO_MESSAGE)) {
            writeWorkbook(message);
            return MILEAGE;
        }
        if (replyToMessage.equals(MILEAGE) && isMileage(message.text())) {
            writer.writeData(message, waybillTemplate);
        }

        return saveToFile(waybillTemplate);
    }

    public void deleteFile(String fileName) {
        try {
            Files.deleteIfExists(Path.of(fileName));
            log.info("Файл был успешно удалён.");
        } catch (IOException e) {
            log.warn("Файла не существует, или он был удалён ранее.");
        }
    }

    private void writeWorkbook(Message message) {
        for (Writable cell : writable) {
            cell.writeData(message, waybillTemplate);
        }
    }

    private String getReplyToMessage(Message message) {
        try {
            return message.replyToMessage().text();
        } catch (Exception e) {
            return EMPTY_STRING;
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
}
