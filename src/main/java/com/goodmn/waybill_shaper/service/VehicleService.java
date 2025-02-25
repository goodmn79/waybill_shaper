package com.goodmn.waybill_shaper.service;

import com.goodmn.waybill_shaper.model.Vehicle;
import com.goodmn.waybill_shaper.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.goodmn.waybill_shaper.extractor.Constant.EMPTY_STRING;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private static final Logger log = LoggerFactory.getLogger(VehicleService.class);
    private final VehicleRepository vehicleRepository;

    public final Vehicle DEFAULT_VEHICLE = new Vehicle()
            .setRegistrationMark(EMPTY_STRING)
            .setMark(EMPTY_STRING)
            .setType(EMPTY_STRING);

    public Vehicle getByRegistrationMark(String registrationMark) {
        Vehicle vehicle= vehicleRepository.findByRegistrationMark(registrationMark)
                .orElseGet(()->{
                    log.error("Данные транспортного средства не получены!");
                    return DEFAULT_VEHICLE;
        });
        log.error("Данные транспортного средства успешно получены.");
        return vehicle;
    }
}
