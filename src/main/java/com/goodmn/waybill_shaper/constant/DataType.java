package com.goodmn.waybill_shaper.constant;

import lombok.Getter;

@Getter
public enum DataType {
    NUMBER("Номер: "),
    DATE("Дата: "),
    TIME("Время: "),
    MILEAGE("Пробег: "),
    CUSTOMER("Заказчик: "),
    VEHICLE_TYPE("Тип ТС: ");

    private final String value;

    DataType(String value) {
        this.value = value;
    }

    public static boolean isCorrectDataType(String value) {
        for (DataType type : DataType.values()) {
            if (value.contains(type.getValue())) {
                return true;
            }
        }
        return false;
    }
}
