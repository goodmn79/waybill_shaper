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
public class CellH65 implements Writable {
    private static final int SHEET = 0;
    private static final int ROW = 64;
    private static final int COLUMN = 7;

    private final Extractable<Time> extractable;

    private final Logger log = LoggerFactory.getLogger(CellH65.class);

    @Override
    public void writeData(Workbook workbook) {
        Cell cell = getCell(workbook, SHEET, ROW, COLUMN);

        String departureTime = extractable.extractData()
                .getEndShiftTime();

        log.debug("Запись данных о времени окончания смены по графику.");
        cell.setCellValue(departureTime);
    }
}
