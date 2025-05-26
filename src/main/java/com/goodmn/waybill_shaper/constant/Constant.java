package com.goodmn.waybill_shaper.constant;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Constant {
    public static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("H:mm", new Locale("ru"));
    public static final String INFO = "Введите данные для формирования путевого листа";
    public static final String SELECT_DATE = "Выберите дату";
    public static final String FILE_NAME = "waybill.xlsm";
    public static final String MILEAGE_INFO = "Введите показание одометра,\nили нажмите кнопку:";
    public static final String NO_DRIVER_DATA = "В документе отсутствуют данные о водителе, поскольку Ваших данных не найдено.\nДля получения заполненного документа предоставьте Ваши данные администратору.";
    public static final String SKIP = "ПРОПУСТИТЬ";
    public static final String EMPTY_STRING = "";
    public static final String POINT = ".";
    public static final String SEPARATOR = "|";
//    public final static String NUMBER = "Номер: ";
//    public final static String DATE = "Дата: ";
//    public final static String TIME = "Время: ";
//    public final static String MILEAGE = "Пробег: ";
//    public final static String CUSTOMER = "Заказчик: ";
//    public final static String VEHICLE_TYPE = "Тип ТС: ";
}
