package com.goodmn.waybill_shaper.writer.impl;

import com.goodmn.waybill_shaper.dto.Number;
import com.goodmn.waybill_shaper.storage.DataStorage;
import com.goodmn.waybill_shaper.writer.WriteableCell;
import com.goodmn.waybill_shaper.writer.Writer;
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
public class NumberDataWriter extends WriteableCell implements Writer {

    private final Logger log = LoggerFactory.getLogger(NumberDataWriter.class);

    private Writer next;

    @Override
    public void writeData(Workbook workbook, DataStorage storage) {
        log.info("Запись данных о номере заказа...");

        Cell AH6 = cell(workbook, 5, 33);
        Cell BX6 = cell(workbook, 5, 75);

        Number number = new Number();

        AH6.setCellValue(number.getWaybillNumber());
        BX6.setCellValue(number.getCouponNumber());
        log.info("Данные о номере заказа успешно записаны.");

        if (this.next != null) {
            this.next.writeData(workbook, storage);
        }
    }
}
