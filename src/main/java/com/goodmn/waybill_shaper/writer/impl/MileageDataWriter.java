package com.goodmn.waybill_shaper.writer.impl;

import com.goodmn.waybill_shaper.dto.Mileage;
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
public class MileageDataWriter extends WriteableCell implements Writer {
    private final DataStorage dataStorage;

    private Writer next;

    @Override
    public void writeData(Workbook workbook, DataStorage storage) {
        log.info("Запись данных одометра...");

        Cell V59 = cell(workbook, 58, 21);

        Mileage mileage = new Mileage();

        V59.setCellValue(mileage.getMileage());
        log.info("Данные одометра успешно записаны.");

        if (this.next != null) {
            this.next.writeData(workbook, storage);
        }
    }
}
