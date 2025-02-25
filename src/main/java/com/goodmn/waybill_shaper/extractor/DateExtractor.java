package com.goodmn.waybill_shaper.extractor;

import com.goodmn.waybill_shaper.model.Date;
import com.goodmn.waybill_shaper.service.DataExtractionUtility;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static com.goodmn.waybill_shaper.constant.Constant.*;

@Component
@RequiredArgsConstructor
public class DateExtractor implements Extractable<Date> {
    private final DataExtractionUtility dataExtractionUtility;

    @Override
    public Date extractData() {

        List<String> orderDate = dataExtractionUtility.getOrderElement(DATE);

        if (orderDate.size() > 1) {
            return new Date()
                    .setNumericDateFormat(getNumericDateFormat(orderDate.get(0)))
                    .setTextDateFormat(getTextDateFormat(orderDate.get(1)));
        } else if (orderDate.size() == 1) {
            return new Date()
                    .setNumericDateFormat(getNumericDateFormat(orderDate.get(0)))
                    .setTextDateFormat(getTextDateFormat(orderDate.get(0)));
        } else {
            return Date.getDefault();
        }
    }

    @Override
    public boolean isPresent() {
        return !this.extractData().equals(Date.getDefault());
    }

    private String extractDate(String orderData) {

        return StringUtils.substringAfter(orderData, DATE);
    }

    private LocalDate getLocalDate(String orderData) {
        String date = extractDate(orderData);

        if (StringUtils.isBlank(date)) {
            return LocalDate.now();
        }
        return LocalDate.parse(date, NUMERIC_DATE_FORMAT);
    }

    private String getNumericDateFormat(String orderData) {
        LocalDate localDate = getLocalDate(orderData);
        return localDate.format(NUMERIC_DATE_FORMAT);
    }

    private String getTextDateFormat(String orderData) {
        LocalDate localDate = getLocalDate(orderData);
        return localDate.format(TEXT_DATE_FORMAT);
    }
}
