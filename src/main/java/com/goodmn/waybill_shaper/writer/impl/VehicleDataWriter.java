package com.goodmn.waybill_shaper.writer.impl;

import com.goodmn.waybill_shaper.model.Vehicle;
import com.goodmn.waybill_shaper.writer.Writeable;
import com.goodmn.waybill_shaper.writer.WriteableCell;
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
public class VehicleDataWriter extends WriteableCell implements Writeable {

    private final Logger log = LoggerFactory.getLogger(VehicleDataWriter.class);

    private Writeable next;

    @Override
    public void writeData(Workbook workbook) {
        log.info("Запись данных о транспортном средстве...");
        Cell BT34 = cell(workbook, 33, 71);
        Cell BX40 = cell(workbook, 39, 75);
        Cell I27 = cell(workbook, 26, 8);
        Cell I32 = cell(workbook, 31, 8);
        Cell T36 = cell(workbook, 35, 19);

        Vehicle vehicle = new Vehicle();

        BT34.setCellValue(vehicle.getMark());
        BX40.setCellValue(vehicle.getRegistrationMark());
        I27.setCellValue(vehicle.getType());
        I32.setCellValue(vehicle.getMark());
        T36.setCellValue(vehicle.getRegistrationMark());
        log.info("Данные о транспортном средстве успешно записаны.");

        if (this.next != null) {
            this.next.writeData(workbook);
        }
    }
}
