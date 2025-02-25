package com.goodmn.waybill_shaper.component;

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
public class VehicleDataWriter implements Writable {
    private final Extractable<Vehicle> extractable;

    private final Logger log = LoggerFactory.getLogger(VehicleDataWriter.class);

    @Override
    public void writeData(Workbook workbook) {
        log.info("Запись данных о транспортном средстве...");
        Cell BT34 = this.cell(workbook, 33, 71);
        Cell BX40 = this.cell(workbook, 39, 75);
        Cell I27 = this.cell(workbook, 26, 8);
        Cell I32 = this.cell(workbook, 31, 8);
        Cell T36 = this.cell(workbook, 35, 19);

        Vehicle vehicle = extractable.extractData();

        BT34.setCellValue(vehicle.getMark());
        BX40.setCellValue(vehicle.getRegistrationMark());
        I27.setCellValue(vehicle.getType());
        I32.setCellValue(vehicle.getMark());
        T36.setCellValue(vehicle.getRegistrationMark());

        if (extractable.isPresent()) {
            log.info("Данные о транспортном средстве успешно записаны.");
        } else {
            log.warn("Данные о транспортном средстве отсутствуют.");
        }
    }
}
