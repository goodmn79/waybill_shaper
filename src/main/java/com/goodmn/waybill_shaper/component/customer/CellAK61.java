package com.goodmn.waybill_shaper.component.customer;

import com.goodmn.waybill_shaper.component.Writable;
import com.goodmn.waybill_shaper.extractor.Extractable;
import com.goodmn.waybill_shaper.model.Customer;
import com.pengrad.telegrambot.model.Message;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CellAK61 implements Writable {
    private static final int SHEET = 0;
    private static final int ROW1 = 60;
    private static final int ROW2 = 65;
    private static final int COLUMN = 36;

    private final Extractable<Customer> extractable;

    private final Logger log = LoggerFactory.getLogger(CellAK61.class);

    @Override
    public void writeData(Message message, Workbook workbook) {

        Cell cell1 = getCell(workbook, SHEET, ROW1, COLUMN);

        String customer = extractable.extractData(message)
                .getCustomer();

        String value1 = StringUtils.substringBefore(customer, " ");

        log.debug("Запись данных о заказчике.");
        cell1.setCellValue(value1);

        String value2 = StringUtils.substringAfter(customer, " ");

        if (StringUtils.isNotBlank(value2)) {
            Cell cell2 = getCell(workbook, SHEET, ROW2, COLUMN);
            cell2.setCellValue(value2);
        }
    }
}
