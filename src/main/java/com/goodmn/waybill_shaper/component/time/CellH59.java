package com.goodmn.waybill_shaper.component.time;

import com.goodmn.waybill_shaper.component.Writable;
import com.goodmn.waybill_shaper.extractor.Extractable;
import com.goodmn.waybill_shaper.model.Time;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CellH59 implements Writable {
    private static final int SHEET = 0;
    private static final int ROW = 58;
    private static final int COLUMN = 7;

    private final Extractable<Time> extractable;

    private final Logger log = LoggerFactory.getLogger(CellH59.class);

    @Override
    public void writeData(Workbook workbook) {
        Cell cell = getCell(workbook, SHEET, ROW, COLUMN);

        String departureTime = extractable.extractData()
                .getStartShiftTime();

        log.debug("Запись данных о времени начала смены по графику.");
        cell.setCellValue(departureTime);
    }
}
