package com.goodmn.waybill_shaper.extractor;

import com.goodmn.waybill_shaper.model.Mileage;
import com.pengrad.telegrambot.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.goodmn.waybill_shaper.extractor.Constant.EMPTY_STRING;
import static com.goodmn.waybill_shaper.model.Mileage.isMileage;

@Component
public class MileageExtractor implements Extractable<Mileage> {

    private final Logger log = LoggerFactory.getLogger(MileageExtractor.class);

    @Override
    public Mileage extractData(Message message) {
        log.debug("Извлечение данных одометра...");

        String text = message.text();
        Mileage mileage = new Mileage();
        if (isMileage(text)) {
            return mileage.setMileage(text);
        }
        return mileage.setMileage(EMPTY_STRING);
    }
}
