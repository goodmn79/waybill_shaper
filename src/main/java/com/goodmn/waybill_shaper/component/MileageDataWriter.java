package com.goodmn.waybill_shaper.component;

import com.goodmn.waybill_shaper.extractor.Extractable;
import com.goodmn.waybill_shaper.model.Mileage;
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
public class MileageDataWriter implements Writeable {
    private final Extractable<Mileage> extractor;

    private final Logger log = LoggerFactory.getLogger(MileageDataWriter.class);

    private Writeable next;

    @Override
    public void writeData(Workbook workbook) {
        log.info("Запись данных одометра...");

        Cell V59 = this.cell(workbook, 58, 21);

        Mileage mileage = extractor.extractData();

        V59.setCellValue(mileage.getMileage());
        log.info("Данные одометра успешно записаны.");

        if (this.next != null) {
            this.next.writeData(workbook);
        }
    }
}
