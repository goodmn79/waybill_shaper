package com.goodmn.waybill_shaper.component.time;

import com.goodmn.waybill_shaper.component.Writable;
import com.goodmn.waybill_shaper.extractor.Extractable;
import com.goodmn.waybill_shaper.model.Time;
import com.pengrad.telegrambot.model.Message;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CellAS66 implements Writable {
    private static final int SHEET = 0;
    private static final int ROW = 65;
    private static final int COLUMN = 44;

    private final Extractable<Time> extractable;

    private final Logger log = LoggerFactory.getLogger(CellAS66.class);

    @Override
    public void writeData(Message message, Workbook workbook) {
        Cell cell = getCell(workbook, SHEET, ROW, COLUMN);

        Time time = extractable.extractData(message);

        log.debug("Запись данных о времени начала второго рейса.");
        if (time.containsBreak()) {
            cell.setCellValue(time.getEndBreak());
        }
    }
}
