package com.goodmn.waybill_shaper.component.driver;

import com.goodmn.waybill_shaper.component.Writable;
import com.goodmn.waybill_shaper.component.customer.CellBS44;
import com.goodmn.waybill_shaper.extractor.Extractable;
import com.goodmn.waybill_shaper.model.Driver;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CellF40 implements Writable {
    private static final int SHEET = 0;
    private static final int ROW = 39;
    private static final int COLUMN = 5;

    private final Extractable<Driver> extractable;

    private final Logger log = LoggerFactory.getLogger(CellBS44.class);

    @Override
    public void writeData(Workbook workbook) {
        Cell cell = getCell(workbook, SHEET, ROW, COLUMN);
        String value = extractable.extractData()
                .getFullName();

        log.debug("Запись данных о водителе.");
        cell.setCellValue(value);
    }
}
