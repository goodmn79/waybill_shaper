package com.goodmn.waybill_shaper.service;

import com.goodmn.waybill_shaper.model.Driver;
import com.goodmn.waybill_shaper.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.goodmn.waybill_shaper.extractor.Constant.EMPTY_STRING;

@Service
@RequiredArgsConstructor
public class DriverService {
    private final DriverRepository driverRepository;
    private final StatusManager statusManager;

    public Driver getDriver(long driverId) {
        return driverRepository.findById(driverId)
                .orElseGet(() -> {
                    statusManager.setAbsenceDriverDataFlag();

                    return new Driver()
                            .setId(0)
                            .setLastName(EMPTY_STRING)
                            .setFirstName(EMPTY_STRING)
                            .setMidlName(EMPTY_STRING)
                            .setDl(EMPTY_STRING)
                            .setSsn(EMPTY_STRING);
                });
    }
}
