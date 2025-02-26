package com.goodmn.waybill_shaper.extractor;

import com.goodmn.waybill_shaper.model.Driver;
import com.goodmn.waybill_shaper.service.DataExtractionUtility;
import com.goodmn.waybill_shaper.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DriverExtractor implements Extractable<Driver> {
    private final DataExtractionUtility dataExtractionUtility;
    private final DriverService driverService;

    private Driver driver;

    @Override
    public Driver extractData() {
        long driverId = dataExtractionUtility.getUserId();
        Driver driver = driverService.getDriver(driverId);
        this.driver = driver;
        return driver;
    }

    @Override
    public boolean isPresent() {
        return !driver.equals(Driver.getDefault());
    }
}
