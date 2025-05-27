package com.goodmn.waybill_shaper.writer.impl;

import com.goodmn.waybill_shaper.dto.Customer;
import com.goodmn.waybill_shaper.storage.DataStorage;
import com.goodmn.waybill_shaper.writer.WriteableCell;
import com.goodmn.waybill_shaper.writer.Writer;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Setter
@RequiredArgsConstructor
public class CustomerDataWriter extends WriteableCell implements Writer {

    private Writer next;

    @Override
    public void writeData(Workbook workbook, DataStorage storage) {
        log.info("Запись данных о заказчике...");

        Cell AK61 = cell(workbook, 60, 36);
        Cell AK66 = cell(workbook, 65, 36);
        Cell BS44 = cell(workbook, 43, 70);

        Customer customer = new Customer();

        String customerData = customer.getCustomer();
        String value1 = StringUtils.substringBefore(customerData, " ");
        String value2 = StringUtils.substringAfter(customerData, " ");

        AK61.setCellValue(value1);
        AK66.setCellValue(value2);
        BS44.setCellValue(customerData);
        log.info("Данные о заказчике успешно записаны.");

        if (this.next != null) {
            this.next.writeData(workbook, storage);
        }
    }
}
