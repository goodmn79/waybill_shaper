package com.goodmn.waybill_shaper.extractor;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Constant {
    public static final DateTimeFormatter NUMERIC_DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final DateTimeFormatter TEXT_DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("ru"));
    public static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("H:mm", new Locale("ru"));
    public static final String FILE_NAME = "waybill.xlsm";
    public static final String MILEAGE_INFO = "Введите показание одометра и отправьте сообщение,\nили нажмите кнопку 'ПРОПУСТИТЬ'";
    public static final String NO_DRIVER_DATA = "В документе отсутствуют данные о водителе, поскольку Ваших данных не найдено.\nДля получения заполненного документа предоставьте Ваши данные администратору.";
    public static final String SKIP = "ПРОПУСТИТЬ";
    public static final String EMPTY_STRING = "";
    public static final String SPACE = " ";
    public final static String NUMBER = "Номер: ";
    public final static String DATE = "Дата: ";
    public final static String TIME = "Время: ";
    public final static String MILEAGE = "Пробег: ";
    public final static String CUSTOMER = "Заказчик: ";
    public final static String VEHICLE_TYPE = "Тип ТС: ";
    public final static String WHITE_REG_MARK_REGEX = "([а-яА-Я]) ?([0-9]{3}) ?([а-яА-Я]{2}) ?([0-9]{2})([0-9]?)";
    public final static String YALOW_REG_MARK_REGEX = "([а-яА-Я]{2}) ?([0-9]{3}) ?([0-9]{2})";
    public final static String WHITE_REG_MARK_REPLACEMENT = "$1 $2 $3 $4$5";
    public final static String YALOW_REG_MARK_REPLACEMENT = "$1 $2 $3";
    public final static String REG_MARK_REGEX = WHITE_REG_MARK_REGEX + "|" + YALOW_REG_MARK_REGEX;
}
