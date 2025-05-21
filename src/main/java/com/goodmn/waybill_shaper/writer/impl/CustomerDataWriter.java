package com.goodmn.waybill_shaper.writer.impl;

import com.goodmn.waybill_shaper.model.Customer;
import com.goodmn.waybill_shaper.writer.Writeable;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Setter
@RequiredArgsConstructor
public class CustomerDataWriter implements Writeable {

    private final Logger log = LoggerFactory.getLogger(CustomerDataWriter.class);

    private Writeable next;

    @Override
    public void writeData(Workbook workbook) {
        log.info("Запись данных о заказчике...");

        Cell AK61 = this.cell(workbook, 60, 36);
        Cell AK66 = this.cell(workbook, 65, 36);
        Cell BS44 = this.cell(workbook, 43, 70);

        Customer customer = new Customer();

        String customerData = customer.getCustomer();
        String value1 = StringUtils.substringBefore(customerData, " ");
        String value2 = StringUtils.substringAfter(customerData, " ");

        AK61.setCellValue(value1);
        AK66.setCellValue(value2);
        BS44.setCellValue(customerData);
        log.info("Данные о заказчике успешно записаны.");

        if (this.next != null) {
            this.next.writeData(workbook);
        }
    }
}
