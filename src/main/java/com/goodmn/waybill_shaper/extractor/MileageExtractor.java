package com.goodmn.waybill_shaper.extractor;

import com.goodmn.waybill_shaper.model.Mileage;
import com.goodmn.waybill_shaper.service.DataExtractionUtility;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.goodmn.waybill_shaper.constant.Constant.EMPTY_STRING;
import static com.goodmn.waybill_shaper.constant.Constant.MILEAGE;

@Component
@RequiredArgsConstructor
public class MileageExtractor implements Extractable<Mileage> {
    private final DataExtractionUtility dataExtractionUtility;

    private final Logger log = LoggerFactory.getLogger(MileageExtractor.class);

    @Override
    public Mileage extractData() {
        log.debug("ИЗВЛЕЧЕНИЕ ДАННЫХ ОДОМЕТРА.");

        List<String> orderMileage = dataExtractionUtility.getOrderElement(MILEAGE);
        if (orderMileage.isEmpty()) {
            log.debug("ДАННЫЕ ОДОМЕТРА ОТСУТСТВУЮТ!.");
            return Mileage.getDefault();
        }
        String mileage = orderMileage.get(0);
        log.debug("ПОЛУЧЕНА СТРОКА С ДАННЫМИ ОДОМЕТРА: {}", mileage);

        return new Mileage()
                .setMileage(extractMileage(mileage));
    }

    @Override
    public boolean isPresent() {
        return !this.extractData().equals(Mileage.getDefault());
    }

    private String extractMileage(String input) {
        String mileage = StringUtils.substringAfter(input, MILEAGE);

        boolean isMileage = StringUtils.isNumeric(mileage);
        log.debug("В СТРОКЕ СОДЕРЖАТЬСЯ ДАННЫЕ ОДОМЕТРА: '{}'", isMileage);

        if (isMileage) {
            log.debug("ПОЛУЧЕНЫ ДАННЫЕ ОДОМЕТРА: {}", mileage);
            return mileage;
        }
        return EMPTY_STRING;
    }
}
