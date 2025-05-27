package com.goodmn.waybill_shaper.command.impl;

import com.goodmn.waybill_shaper.cleaner.ChatCleaner;
import com.goodmn.waybill_shaper.command.Command;
import com.goodmn.waybill_shaper.executor.TelegramBotExecutor;
import com.goodmn.waybill_shaper.storage.DataStorage;
import com.goodmn.waybill_shaper.writer.Writer;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendDocument;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.goodmn.waybill_shaper.constant.Cmd.GENERATE_WAYBILL_CMD;
import static com.goodmn.waybill_shaper.constant.Constant.FILE_NAME;

@Slf4j
@Component(GENERATE_WAYBILL_CMD)
@RequiredArgsConstructor
public class GenerateWaybillCmd implements Command {
    private final Writer writer;
    private final DataStorage storage;
    private final Workbook waybill;
    private final ChatCleaner chatCleaner;
    private final TelegramBotExecutor executor;

    @Override
    public String cmd() {
        return GENERATE_WAYBILL_CMD;
    }

    @Override
    public void execute(Update update) {
        long chatId = update.callbackQuery().from().id();

        chatCleaner.deleteLastMessage(chatId);
        chatCleaner.deleteInputMessage(update);

        writer.writeData(waybill, storage);

        executor.sendDocument(new SendDocument(chatId, this.saveToFile(waybill)));
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
