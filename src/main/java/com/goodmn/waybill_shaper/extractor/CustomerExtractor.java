package com.goodmn.waybill_shaper.extractor;

import com.goodmn.waybill_shaper.model.Customer;
import com.goodmn.waybill_shaper.service.DataExtractionUtility;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.goodmn.waybill_shaper.constant.DataType.CUSTOMER;


@Component
@RequiredArgsConstructor
public class CustomerExtractor implements Extractable<Customer> {
    private final DataExtractionUtility dataExtractionUtility;

    private final Logger log = LoggerFactory.getLogger(CustomerExtractor.class);

    @Override
    public Customer extractData() {
        log.debug("Извлечение данных о заказчике...");

        List<String> orderCustomer = dataExtractionUtility.getOrderElement(CUSTOMER);

        if (orderCustomer.isEmpty()) {
            return Customer.getDefault();
        }

        String customer = extractCustomerData(orderCustomer.get(0));

        log.debug("ЗАКАЗЧИК: '{}'", customer);
        return new Customer()
                .setCustomer(customer);
    }

    @Override
    public boolean isPresent(Customer customer) {
        return !Customer.getDefault().equals(customer);
    }

    private String extractCustomerData(String orderData) {
        log.debug("СТРОКА С ДАННЫМИ О ЗАКАЗЧИКЕ: '{}'.", orderData);

        String data = StringUtils.substringAfter(orderData, CUSTOMER.getValue());
        if (StringUtils.isBlank(data)) {
            log.debug("Данные о заказчике не получены!");
        } else {
            log.debug("Данные о заказчике получены успешно.");
        }
        return data;
    }
}
