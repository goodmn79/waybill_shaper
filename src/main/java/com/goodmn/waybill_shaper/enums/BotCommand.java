package com.goodmn.waybill_shaper.enums;

import lombok.Getter;

@Getter
public enum BotCommand {
    START("/start"),
    DATE("Дата"),
    CUSTOMER("Заказчик"),
    VEHICLE("Транспортное средство"),
    MILEAGE("Пробег");

    private final String command;

    @Override
    public String toString() {
        return command;
    }

    BotCommand(String command) {
        this.command = command;
    }

}
