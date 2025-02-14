package com.goodmn.waybill_shaper.component.vehicle;

import com.goodmn.waybill_shaper.component.Writable;
import com.goodmn.waybill_shaper.extractor.Extractable;
import com.goodmn.waybill_shaper.model.Vehicle;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CellBT34 implements Writable {
    private static final int SHEET = 0;
    private static final int ROW = 33;
    private static final int COLUMN = 71;

    private final Extractable<Vehicle> extractable;

    private final Logger log = LoggerFactory.getLogger(CellBT34.class);

    @Override
    public void writeData(Workbook workbook) {
        Cell cell = getCell(workbook, SHEET, ROW, COLUMN);
        String value = extractable.extractData()
                .getMark();

        log.debug("Запись данных о транспортном средстве.");
        cell.setCellValue(value);
    }
}
