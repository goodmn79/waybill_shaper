package com.goodmn.waybill_shaper.extractor;

import com.goodmn.waybill_shaper.model.Vehicle;
import com.goodmn.waybill_shaper.service.DataExtractionUtility;
import com.goodmn.waybill_shaper.service.VehicleService;
import lombok.RequiredArgsConstructor;
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

    @Override
    public Vehicle extractData() {

        List<String> orderVehicle = dataExtractionUtility.getOrderElement(VEHICLE_TYPE);

        if (orderVehicle.isEmpty()) {
            return Vehicle.getDefault();
        }

        String registrationMarks =
                formatRegistrationMark(extractRegistrationMark(orderVehicle.get(0)));

        return vehicleService.getByRegistrationMark(registrationMarks);
    }

    @Override
    public boolean isPresent() {
        return !this.extractData().equals(Vehicle.getDefault());
    }

    private String extractRegistrationMark(String orderData) {

        Pattern pattern = Pattern.compile(REG_MARK_REGEX);
        Matcher matcher = pattern.matcher(orderData);
        if (matcher.find()) {
            return matcher.group();
        }
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
