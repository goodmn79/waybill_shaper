package com.goodmn.waybill_shaper.component.number;

import com.goodmn.waybill_shaper.component.Writable;
import com.goodmn.waybill_shaper.extractor.Extractable;
import com.goodmn.waybill_shaper.model.Number;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CellBX6 implements Writable {
    private static final int SHEET = 0;
    private static final int ROW = 5;
    private static final int COLUMN = 75;

    private final Extractable<Number> extractable;

    private final Logger log = LoggerFactory.getLogger(CellBX6.class);

    @Override
    public void writeData(Workbook workbook) {

        Cell cell = getCell(workbook, SHEET, ROW, COLUMN);
        String value = extractable.extractData()
                .getCouponNumber();
        log.debug("Запись данных о номере заказа.");
        cell.setCellValue(value);
    }
}
