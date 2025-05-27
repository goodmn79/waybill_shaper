package com.goodmn.waybill_shaper.writer.impl;

import com.goodmn.waybill_shaper.dto.DateData;
import com.goodmn.waybill_shaper.storage.DataStorage;
import com.goodmn.waybill_shaper.writer.WriteableCell;
import com.goodmn.waybill_shaper.writer.Writer;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import static com.goodmn.waybill_shaper.constant.ButtonName.DATE;

@Slf4j
@Component
@Setter
@RequiredArgsConstructor
public class DateDataWriter extends WriteableCell implements Writer {
    private Writer next;

    @Override
    public void writeData(Workbook workbook, DataStorage storage) {
        log.info("Запись данных о дате заказа...");

        Cell AC59 = cell(workbook, 58, 28);
        Cell AP85 = cell(workbook, 84, 41);
        Cell BI229 = cell(workbook, 228, 60);
        Cell BR10 = cell(workbook, 9, 69);
        Cell M11 = cell(workbook, 10, 12);
        Cell Z11 = cell(workbook, 10, 25);

        String date = storage.getData(DATE);
        DateData dateData = new DateData(date);

        String numericDateFormat = dateData.numericDateFormat();
        String textDateFormat = dateData.textDateFormat();

        AC59.setCellValue(numericDateFormat);
        AP85.setCellValue(numericDateFormat);
        BI229.setCellValue(numericDateFormat);
        BR10.setCellValue(textDateFormat);
        M11.setCellValue(textDateFormat);
        Z11.setCellValue(textDateFormat);

        log.info("Данные о дате заказа успешно записаны.");

        if (this.next != null) {
            this.next.writeData(workbook, storage);
        }
    }
}
