package com.goodmn.waybill_shaper.component;

import com.goodmn.waybill_shaper.extractor.Extractable;
import com.goodmn.waybill_shaper.model.Mileage;
import com.pengrad.telegrambot.model.Message;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@RequiredArgsConstructor
public class MileageWriter implements Writable {
    private static final int SHEET = 0;
    private static final int ROW = 58;
    private static final int COLUMN = 21;

    private final Extractable<Mileage> extractable;

    private final Logger log = LoggerFactory.getLogger(MileageWriter.class);

    @Override
    public void writeData(Message message, Workbook workbook) {
        Cell cell = getCell(workbook, SHEET, ROW, COLUMN);
        String value = extractable.extractData(message)
                .getMileage();

        log.debug("Запись данных одометра.");
        cell.setCellValue(value);
    }
}
