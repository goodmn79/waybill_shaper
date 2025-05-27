package com.goodmn.waybill_shaper.writer.impl;

import com.goodmn.waybill_shaper.dto.Driver;
import com.goodmn.waybill_shaper.storage.DataStorage;
import com.goodmn.waybill_shaper.writer.WriteableCell;
import com.goodmn.waybill_shaper.writer.Writer;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Primary
@Order(0)
@Setter
@ToString
@RequiredArgsConstructor
public class DriverDataWriter extends WriteableCell implements Writer {

    private Writer next;

    @Override
    public void writeData(Workbook workbook, DataStorage storage) {
        log.info("Запись данных о водителе...");

        Cell AH40 = cell(workbook, 39, 33);
        Cell AT141 = cell(workbook, 140, 45);
        Cell F40 = cell(workbook, 39, 5);
        Cell J44 = cell(workbook, 43, 9);
        Cell W141 = cell(workbook, 140, 22);

        Driver driver = new Driver();

        AH40.setCellValue(driver.getSsn());
        AT141.setCellValue(driver.getLastNameAndInitials());
        F40.setCellValue(driver.getFullName());
        J44.setCellValue(driver.getDl());
        W141.setCellValue(driver.getLastNameAndInitials());
        log.info("Данные о водителе успешно записаны.");

        if (this.next != null) {
            this.next.writeData(workbook, storage);
        }
    }
}
