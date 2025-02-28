package com.goodmn.waybill_shaper.extractor;

import com.goodmn.waybill_shaper.model.Number;
import com.goodmn.waybill_shaper.service.DataExtractionUtility;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.goodmn.waybill_shaper.constant.Constant.EMPTY_STRING;
import static com.goodmn.waybill_shaper.constant.DataType.NUMBER;

@Component
@RequiredArgsConstructor
public class NumberExtractor implements Extractable<Number> {
    private final DataExtractionUtility dataExtractionUtility;

    private final Logger log = LoggerFactory.getLogger(NumberExtractor.class);

    @Override
    public Number extractData() {
        log.debug("Извлечение данных о номере заказа...");

        List<String> orderNumber = dataExtractionUtility.getOrderElement(NUMBER);

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

        log.debug("Номер заказа: '{}', номер талона: '{}'", waybillNumber, couponNumber);
        return new Number()
                .setWaybillNumber(waybillNumber)
                .setCouponNumber(couponNumber);
    }

    @Override
    public boolean isPresent(Number number) {
        return !Number.getDefault().equals(number);
    }

    private String extractNumber(String orderData) {
        log.debug("Строка с данными о номере заказа: '{}'.", orderData);

        String number = StringUtils.substringAfter(orderData, NUMBER.getValue());
        if (StringUtils.isBlank(number)) {
            log.debug("Данные о номере заказа не получены!");
            return EMPTY_STRING;
        } else {
            log.debug("Данные о номере заказа получены успешно.");
            return number.trim();
        }
    }
}
