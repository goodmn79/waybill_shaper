package com.goodmn.waybill_shaper.writer.impl;

import com.goodmn.waybill_shaper.storage.DataStorage;
import com.goodmn.waybill_shaper.writer.WriteableCell;
import com.goodmn.waybill_shaper.writer.Writer;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import static com.goodmn.waybill_shaper.constant.ButtonName.MILEAGE;

@Slf4j
@Component
@Setter
@RequiredArgsConstructor
public class MileageDataWriter extends WriteableCell implements Writer {

    private Writer next;

    @Override
    public void writeData(Workbook workbook, DataStorage storage) {
        log.info("Запись данных одометра...");

        Cell V59 = cell(workbook, 58, 21);

        String mileage = storage.getData(MILEAGE);

        V59.setCellValue(mileage);
        log.info("Данные одометра успешно записаны.");

        if (this.next != null) {
            this.next.writeData(workbook, storage);
        }
    }
}
