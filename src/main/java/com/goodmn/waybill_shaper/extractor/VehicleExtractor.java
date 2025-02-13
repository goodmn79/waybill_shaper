package com.goodmn.waybill_shaper.extractor;

import com.goodmn.waybill_shaper.model.Vehicle;
import com.goodmn.waybill_shaper.service.MessageHandler;
import com.goodmn.waybill_shaper.service.VehicleService;
import com.pengrad.telegrambot.model.Message;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.goodmn.waybill_shaper.extractor.Constant.*;

@Component
@RequiredArgsConstructor
public class VehicleExtractor implements Extractable<Vehicle> {
    private final MessageHandler messageHandler;
    private final VehicleService vehicleService;

    private final Logger log = LoggerFactory.getLogger(VehicleExtractor.class);

    @Override
    public Vehicle extractData(Message message) {
        log.debug("Извлечение данных о транспортном средстве...");

        List<String> orderData = messageHandler.getOrderElement(message, VEHICLE_TYPE);
        if (orderData.isEmpty()) {
            return new Vehicle()
                    .setRegistrationMark(EMPTY_STRING)
                    .setMark(EMPTY_STRING)
                    .setType(EMPTY_STRING);
        }

        String registrationMarks = extractRegistrationMark(orderData.get(0));

        log.debug("РЕГИСТРАЦИОННЫЙ ЗНАК ТС: '{}'", registrationMarks);
        return vehicleService.findById(formatRegistrationMark(registrationMarks));
    }

    private String extractRegistrationMark(String orderData) {
        log.debug("СТРОКА С ДАННЫМИ О ТРАНСПОРТНОМ СРЕДСТВЕ: '{}'.", orderData);

        Pattern pattern = Pattern.compile(REG_MARK_REGEX);
        Matcher matcher = pattern.matcher(orderData);
        if (matcher.find()) {
            log.debug("Данные о транспортном средстве получены успешно.");
            return matcher.group();
        }
        log.debug("Данные о транспортном средстве не получены!");
        return EMPTY_STRING;
    }

    private String formatRegistrationMark(String registrationMark) {
        if (registrationMark.matches(WHITE_REG_MARK_REGEX)) {
            return registrationMark.replaceAll(WHITE_REG_MARK_REGEX, WHITE_REG_MARK_REPLACEMENT).toUpperCase();
        }
        return registrationMark.replaceAll(YALOW_REG_MARK_REGEX, YALOW_REG_MARK_REPLACEMENT).toUpperCase();
    }
}
