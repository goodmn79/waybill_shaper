package com.goodmn.waybill_shaper.component.time;

import com.goodmn.waybill_shaper.component.Writable;
import com.goodmn.waybill_shaper.component.customer.CellAK61;
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
public class CellBP229 implements Writable {
    private static final int SHEET = 0;
    private static final int ROW = 228;
    private static final int COLUMN = 67;

    private final Extractable<Time> extractable;

    private final Logger log = LoggerFactory.getLogger(CellAK61.class);

    @Override
    public void writeData(Message message, Workbook workbook) {
        Cell cell = getCell(workbook, SHEET, ROW, COLUMN);

        String startBreak = extractable.extractData(message)
                .getStartBreak();

        log.debug("Запись данных о времени начала внутрисменного перерыва.");
        cell.setCellValue(startBreak);
    }
}
