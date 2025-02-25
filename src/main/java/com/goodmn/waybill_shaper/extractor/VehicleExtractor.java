package com.goodmn.waybill_shaper.extractor;

import com.goodmn.waybill_shaper.model.Vehicle;
import com.goodmn.waybill_shaper.service.DataExtractionUtility;
import com.goodmn.waybill_shaper.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.goodmn.waybill_shaper.constant.Constant.*;

@Component
@RequiredArgsConstructor
public class VehicleExtractor implements Extractable<Vehicle> {
    private final DataExtractionUtility dataExtractionUtility;
    private final VehicleService vehicleService;

    private final Logger log = LoggerFactory.getLogger(VehicleExtractor.class);

    @Override
    public Vehicle extractData() {
        log.debug("ИЗВЛЕЧЕНИЕ ДАННЫХ О ТРАНСПОРТНОМ СРЕДСТВЕ!");

        List<String> orderVehicle = dataExtractionUtility.getOrderElement(VEHICLE_TYPE);

        if (orderVehicle.isEmpty()) {
            return vehicleService.DEFAULT_VEHICLE;
        }

        String registrationMarks =
                formatRegistrationMark(extractRegistrationMark(orderVehicle.get(0)));

        Vehicle vehicle = vehicleService.getByRegistrationMark(registrationMarks);
        log.debug("РЕГИСТРАЦИОННЫЙ ЗНАК ТС: '{}'", vehicle.getRegistrationMark());

        return vehicle;
    }

    private String extractRegistrationMark(String orderData) {
        log.debug("СТРОКА С ДАННЫМИ О ТРАНСПОРТНОМ СРЕДСТВЕ: '{}'.", orderData);

        Pattern pattern = Pattern.compile(REG_MARK_REGEX);
        Matcher matcher = pattern.matcher(orderData);
        if (matcher.find()) {
            log.debug("ДАННЫЕ О ТРАНСПОРТНОМ СРЕДСТВЕ УСПЕШНО ПОЛУЧЕНЫ.");
            return matcher.group();
        }
        log.debug("ДАННЫЕ О ТРАНСПОРТНОМ СРЕДСТВЕ ОТСУТСТВУЮТ!");
        return EMPTY_STRING;
    }

    private String formatRegistrationMark(String registrationMark) {
        if (registrationMark.matches(WHITE_REG_MARK_REGEX)) {
            return registrationMark
                    .replaceAll(WHITE_REG_MARK_REGEX, WHITE_REG_MARK_REPLACEMENT).toUpperCase();
        }
        return registrationMark
                .replaceAll(YALOW_REG_MARK_REGEX, YALOW_REG_MARK_REPLACEMENT).toUpperCase();
    }
}
