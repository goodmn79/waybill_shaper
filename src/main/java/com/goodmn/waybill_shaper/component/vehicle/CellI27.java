package com.goodmn.waybill_shaper.component.vehicle;

import com.goodmn.waybill_shaper.component.Writable;
import com.goodmn.waybill_shaper.extractor.Extractable;
import com.goodmn.waybill_shaper.model.Vehicle;
import com.pengrad.telegrambot.model.Message;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CellI27 implements Writable {
    private static final int SHEET = 0;
    private static final int ROW = 26;
    private static final int COLUMN = 8;

    private final Extractable<Vehicle> extractable;

    private final Logger log = LoggerFactory.getLogger(CellI27.class);

    @Override
    public void writeData(Message message, Workbook workbook) {
        Cell cell = getCell(workbook, SHEET, ROW, COLUMN);
        String value = extractable.extractData(message)
                .getType();

        log.debug("Запись данных о транспортном средстве.");
        cell.setCellValue(value);
    }
}
