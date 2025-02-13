package com.goodmn.waybill_shaper.extractor;

import com.goodmn.waybill_shaper.model.Customer;
import com.goodmn.waybill_shaper.service.MessageHandler;
import com.pengrad.telegrambot.model.Message;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.goodmn.waybill_shaper.extractor.Constant.CUSTOMER;
import static com.goodmn.waybill_shaper.extractor.Constant.EMPTY_STRING;

@Component
@RequiredArgsConstructor
public class CustomerExtractor implements Extractable<Customer> {
    private final MessageHandler messageHandler;

    private final Logger log = LoggerFactory.getLogger(CustomerExtractor.class);

    @Override
    public Customer extractData(Message message) {
        log.debug("Извлечение данных о заказчике...");

        List<String> orderCustomer = messageHandler.getOrderElement(message, CUSTOMER);
        if (orderCustomer.isEmpty()) {
            return new Customer()
                    .setCustomer(EMPTY_STRING);
        }

        String customer = extractCustomerData(orderCustomer.get(0));

        log.debug("ЗАКАЗЧИК: '{}'", customer);
        return new Customer()
                .setCustomer(customer);
    }

    private String extractCustomerData(String orderData) {
        log.debug("СТРОКА С ДАННЫМИ О ЗАКАЗЧИКЕ: '{}'.", orderData);

        String data = StringUtils.substringAfter(orderData, CUSTOMER);
        if (StringUtils.isBlank(data)) {
            log.debug("Данные о заказчике не получены!");
        } else {
            log.debug("Данные о заказчике получены успешно.");
        }
        return data;
    }
}
