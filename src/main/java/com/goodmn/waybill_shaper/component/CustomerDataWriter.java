package com.goodmn.waybill_shaper.component;

import com.goodmn.waybill_shaper.extractor.Extractable;
import com.goodmn.waybill_shaper.model.Customer;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerDataWriter implements Writable {
    private final Extractable<Customer> extractor;

    private final Logger log = LoggerFactory.getLogger(CustomerDataWriter.class);

    @Override
    public void writeData(Workbook workbook) {
        log.info("Запись данных о заказчике...");

        Cell AK61 = this.cell(workbook, 60, 36);
        Cell AK66 = this.cell(workbook, 65, 36);
        Cell BS44 = this.cell(workbook, 43, 70);

        Customer customer = extractor.extractData();

        String customerData = customer.getCustomer();

        String value1 = StringUtils.substringBefore(customerData, " ");
        AK61.setCellValue(value1);

        String value2 = StringUtils.substringAfter(customerData, " ");

        if (StringUtils.isNotBlank(value2)) {
            AK66.setCellValue(value2);
        }
        BS44.setCellValue(customerData);

        if (extractor.isPresent(customer)) {
            log.info("Данные о заказчике успешно записаны.");
        } else {
            log.warn("Данные о заказчике отсутствуют.");
        }
    }
}
