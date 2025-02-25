package com.goodmn.waybill_shaper.component;

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
public class TimeDataWritable implements Writable {
    private final Extractable<Time> extractable;

    private final Logger log = LoggerFactory.getLogger(TimeDataWritable.class);

    @Override
    public void writeData(Workbook workbook) {
        log.info("Запись данных о времени заказа...");

        Cell AS61 = this.cell(workbook, 60, 44);
        Cell AS66 = this.cell(workbook, 65, 44);
        Cell AX61 = this.cell(workbook, 60, 49);
        Cell AX66 = this.cell(workbook, 65, 49);
        Cell BP229 = this.cell(workbook, 228, 67);
        Cell BU69 = this.cell(workbook, 68, 72);
        Cell BU73 = this.cell(workbook, 72, 72);
        Cell BX229 = this.cell(workbook, 228, 75);
        Cell H59 = this.cell(workbook, 58, 7);
        Cell H65 = this.cell(workbook, 64, 7);

        Time time = extractable.extractData();

        AS61.setCellValue(time.getDepartureTime());
        if (time.containsBreak()) {
            AS66.setCellValue(time.getDepartureTime());
            AX61.setCellValue(time.getDepartureTime());
            AX66.setCellValue(time.getArrivalTime());
        } else {
            AX61.setCellValue(time.getArrivalTime());
        }
        BP229.setCellValue(time.getStartBreak());
        BU69.setCellValue(time.getDepartureTime());
        BU73.setCellValue(time.getArrivalTime());
        BX229.setCellValue(time.getEndBreak());
        H59.setCellValue(time.getStartShiftTime());
        H65.setCellValue(time.getEndShiftTime());

        if (extractable.isPresent()) {
            log.info("Данные о времени заказа успешно записаны.");
        } else {
            log.warn("Данные о времени заказа отсутствуют.");
        }
    }
}
