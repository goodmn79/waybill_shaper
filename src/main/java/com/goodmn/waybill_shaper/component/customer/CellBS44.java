package com.goodmn.waybill_shaper.component.customer;

import com.goodmn.waybill_shaper.component.Writable;
import com.goodmn.waybill_shaper.extractor.Extractable;
import com.goodmn.waybill_shaper.model.Customer;
import com.pengrad.telegrambot.model.Message;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CellBS44 implements Writable {
    private static final int SHEET = 0;
    private static final int ROW = 43;
    private static final int COLUMN = 70;

    private final Extractable<Customer> extractable;

    private final Logger log = LoggerFactory.getLogger(CellBS44.class);

    @Override
    public void writeData(Message message, Workbook workbook) {

        Cell cell = getCell(workbook, SHEET, ROW, COLUMN);
        String value = extractable.extractData(message)
                .getCustomer();

        log.debug("Запись данных о заказчике.");
        cell.setCellValue(value);
    }
}
