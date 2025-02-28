package com.goodmn.waybill_shaper.component;

import com.goodmn.waybill_shaper.extractor.Extractable;
import com.goodmn.waybill_shaper.model.Mileage;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@RequiredArgsConstructor
public class MileageDataWriter implements Writable {
    private final Extractable<Mileage> extractable;

    private final Logger log = LoggerFactory.getLogger(MileageDataWriter.class);

    @Override
    public void writeData(Workbook workbook) {
        log.info("Запись данных одометра...");

        Cell V59 = this.cell(workbook, 58, 21);

        Mileage mileage = extractable.extractData();
        V59.setCellValue(mileage.getMileage());

        if (extractable.isPresent(mileage)) {
            log.info("Данные одометра успешно записаны.");
        } else {
            log.warn("Данные одометра отсутствуют.");
        }
    }
}
