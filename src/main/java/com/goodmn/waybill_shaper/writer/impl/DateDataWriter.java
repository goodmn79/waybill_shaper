package com.goodmn.waybill_shaper.writer.impl;

import com.goodmn.waybill_shaper.model.Date;
import com.goodmn.waybill_shaper.writer.Writeable;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Setter
@RequiredArgsConstructor
public class DateDataWriter implements Writeable {

    private final Logger log = LoggerFactory.getLogger(DateDataWriter.class);

    private Writeable next;

    @Override
    public void writeData(Workbook workbook) {
        log.info("Запись данных о дате заказа...");

        Cell AC59 = this.cell(workbook, 58, 28);
        Cell AP85 = this.cell(workbook, 84, 41);
        Cell BI229 = this.cell(workbook, 228, 60);
        Cell BR10 = this.cell(workbook, 9, 69);
        Cell M11 = this.cell(workbook, 10, 12);
        Cell Z11 = this.cell(workbook, 10, 25);

        Date date = new Date();

        String numericDateFormat = date.getNumericDateFormat();
        String textDateFormat = date.getTextDateFormat();

        AC59.setCellValue(numericDateFormat);
        AP85.setCellValue(numericDateFormat);
        BI229.setCellValue(numericDateFormat);
        BR10.setCellValue(textDateFormat);
        M11.setCellValue(textDateFormat);
        Z11.setCellValue(textDateFormat);
        log.info("Данные о дате заказа успешно записаны.");

        if (this.next != null) {
            this.next.writeData(workbook);
        }
    }
}
