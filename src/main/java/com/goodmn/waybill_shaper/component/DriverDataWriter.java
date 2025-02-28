package com.goodmn.waybill_shaper.component;

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
public class DriverDataWriter implements Writable {
    private final Extractable<Driver> extractable;

    private final Logger log = LoggerFactory.getLogger(DriverDataWriter.class);

    @Override
    public void writeData(Workbook workbook) {
        log.info("Запись данных о водителе...");

        Cell AH40 = this.cell(workbook, 39, 33);
        Cell AT141 = this.cell(workbook, 140, 45);
        Cell F40 = this.cell(workbook, 39, 5);
        Cell J44 = this.cell(workbook, 43, 9);
        Cell W141 = this.cell(workbook, 140, 22);

        Driver driver = extractable.extractData();

        AH40.setCellValue(driver.getSsn());
        AT141.setCellValue(driver.getLastNameAndInitials());
        F40.setCellValue(driver.getFullName());
        J44.setCellValue(driver.getDl());
        W141.setCellValue(driver.getLastNameAndInitials());

        if (extractable.isPresent(driver)) {
            log.info("Данные о водителе успешно записаны.");
        } else {
            log.warn("Данные о водителе отсутствуют.");
        }
    }
}
