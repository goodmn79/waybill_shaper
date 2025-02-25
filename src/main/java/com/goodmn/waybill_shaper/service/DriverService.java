package com.goodmn.waybill_shaper.service;

import com.goodmn.waybill_shaper.model.Driver;
import com.goodmn.waybill_shaper.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.goodmn.waybill_shaper.constant.Constant.EMPTY_STRING;

@Service
@RequiredArgsConstructor
public class DriverService {
    private final DriverRepository driverRepository;
    private final StatusManager statusManager;

    Logger log = LoggerFactory.getLogger(DriverService.class);

    public Driver getDriver(long driverId) {
        Driver driver = driverRepository.findById(driverId)
                .orElseGet(() -> {
                    log.error("Данные водителя не получены!");

                    statusManager.setAbsenceDriverDataFlag();

                    return new Driver()
                            .setId(0)
                            .setLastName(EMPTY_STRING)
                            .setFirstName(EMPTY_STRING)
                            .setMidlName(EMPTY_STRING)
                            .setDl(EMPTY_STRING)
                            .setSsn(EMPTY_STRING);
                });
        log.info("Получены данные водителя по идентификатору: \"{}\"", driverId);
        return driver;
    }
}
