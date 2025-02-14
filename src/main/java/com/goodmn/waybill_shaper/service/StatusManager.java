package com.goodmn.waybill_shaper.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class StatusManager {
    private int waitingDataStatusFlag = 0;
    private int driverDataFlag = 0;

    Logger log = LoggerFactory.getLogger(StatusManager.class);

    public boolean isWaitingDataStatus() {
        boolean isWaitingDataStatus = this.waitingDataStatusFlag > 0;
        log.debug("Статус ожидания установлен: {}", isWaitingDataStatus);

        return isWaitingDataStatus;
    }

    public void setWaitingDataStatus() {
        this.waitingDataStatusFlag = 1;

        log.warn("Установлен статус ожидания!");
    }

    public void removeWaitingDataStatus() {
        this.waitingDataStatusFlag = 0;

        log.info("Статус ожидания снят!");
    }

    public boolean noDriverData() {
        return driverDataFlag > 0;
    }

    public void setAbsenceDriverDataFlag() {
        this.driverDataFlag = 1;
    }

    public void removeAbsenceDriverDataFlag() {
        this.driverDataFlag = 0;
    }
}
