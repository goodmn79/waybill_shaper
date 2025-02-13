package com.goodmn.waybill_shaper.component.numeric_date;

import com.goodmn.waybill_shaper.component.Writable;
import com.goodmn.waybill_shaper.extractor.Extractable;
import com.goodmn.waybill_shaper.model.Date;
import com.pengrad.telegrambot.model.Message;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CellAC59 implements Writable {
    private static final int SHEET = 0;
    private static final int ROW = 58;
    private static final int COLUMN = 28;

    private final Extractable<Date> extractable;

    private final Logger log = LoggerFactory.getLogger(CellAC59.class);

    @Override
    public void writeData(Message message, Workbook workbook) {

        Cell cell = getCell(workbook, SHEET, ROW, COLUMN);
        String value = extractable.extractData(message)
                .getNumericDateFormat();

        log.debug("Запись данных о дате заказа.");
        cell.setCellValue(value);
    }
}
