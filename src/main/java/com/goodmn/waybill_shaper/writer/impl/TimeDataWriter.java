package com.goodmn.waybill_shaper.writer.impl;

import com.goodmn.waybill_shaper.dto.Time;
import com.goodmn.waybill_shaper.storage.DataStorage;
import com.goodmn.waybill_shaper.writer.WriteableCell;
import com.goodmn.waybill_shaper.writer.Writer;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Setter
@RequiredArgsConstructor
public class TimeDataWriter extends WriteableCell implements Writer {

    private Writer next;

    @Override
    public void writeData(Workbook workbook, DataStorage storage) {
        log.info("Запись данных о времени заказа...");

        Cell AS61 = cell(workbook, 60, 44);
        Cell AS66 = cell(workbook, 65, 44);
        Cell AX61 = cell(workbook, 60, 49);
        Cell AX66 = cell(workbook, 65, 49);
        Cell BP229 = cell(workbook, 228, 67);
        Cell BU69 = cell(workbook, 68, 72);
        Cell BU73 = cell(workbook, 72, 72);
        Cell BX229 = cell(workbook, 228, 75);
        Cell H59 = cell(workbook, 58, 7);
        Cell H65 = cell(workbook, 64, 7);

        Time time = new Time();

        AS61.setCellValue(time.getDepartureTime());
        if (time.containsBreak()) {
            AX61.setCellValue(time.getStartBreak());
            AS66.setCellValue(time.getEndBreak());
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
        log.info("Данные о времени заказа успешно записаны.");

        if (this.next != null) {
            this.next.writeData(workbook, storage);
        }
    }
}
