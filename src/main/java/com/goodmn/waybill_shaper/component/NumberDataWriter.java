package com.goodmn.waybill_shaper.component;

import com.goodmn.waybill_shaper.extractor.Extractable;
import com.goodmn.waybill_shaper.model.Number;
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
public class NumberDataWriter implements Writeable {
    private final Extractable<Number> extractor;

    private final Logger log = LoggerFactory.getLogger(NumberDataWriter.class);

    private Writeable next;

    @Override
    public void writeData(Workbook workbook) {
        log.info("Запись данных о номере заказа...");

        Cell AH6 = this.cell(workbook, 5, 33);
        Cell BX6 = this.cell(workbook, 5, 75);

        Number number = extractor.extractData();

        AH6.setCellValue(number.getWaybillNumber());
        BX6.setCellValue(number.getCouponNumber());
        log.info("Данные о номере заказа успешно записаны.");

        if (this.next != null) {
            this.next.writeData(workbook);
        }
    }
}
