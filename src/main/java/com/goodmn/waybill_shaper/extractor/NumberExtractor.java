package com.goodmn.waybill_shaper.extractor;

import com.goodmn.waybill_shaper.model.Number;
import com.goodmn.waybill_shaper.service.MessageHandler;
import com.pengrad.telegrambot.model.Message;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.goodmn.waybill_shaper.extractor.Constant.*;

@Component
@RequiredArgsConstructor
public class NumberExtractor implements Extractable<Number> {
    private final MessageHandler messageHandler;

    private final Logger log = LoggerFactory.getLogger(NumberExtractor.class);

    @Override
    public Number extractData(Message message) {
        log.debug("Извлечение данных о номере заказа...");

        List<String> orderNumber = messageHandler.getOrderElement(message, NUMBER);

        String waybillNumber;
        String couponNumber;
        if (orderNumber.size() > 1) {
            waybillNumber = extractNumber(orderNumber.get(0));
            couponNumber = extractNumber(orderNumber.get(1));
        } else if (orderNumber.size() == 1) {
            waybillNumber = extractNumber(orderNumber.get(0));
            couponNumber = extractNumber(orderNumber.get(0));
        } else {
            waybillNumber = EMPTY_STRING;
            couponNumber = waybillNumber;
        }

        log.debug("НОМЕР ЗАКАЗА: '{}', НОМЕР ТАЛОНА: '{}'", waybillNumber, couponNumber);
        return new Number()
                .setWaybillNumber(waybillNumber)
                .setCouponNumber(couponNumber);
    }

    private String extractNumber(String orderData) {
        log.debug("СТРОКА С ДАННЫМИ О НОМЕРЕ ЗАКАЗА: '{}'.", orderData);

        String number = StringUtils.substringAfter(orderData, NUMBER);
        if (StringUtils.isBlank(number)) {
            log.debug("Данные о номере заказа не получены!");
            return EMPTY_STRING;
        } else {
            log.debug("Данные о номере заказа получены успешно.");
            return number.trim();
        }
    }
}
