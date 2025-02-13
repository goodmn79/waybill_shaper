package com.goodmn.waybill_shaper.extractor;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Constant {
    public static final DateTimeFormatter NUMERIC_DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final DateTimeFormatter TEXT_DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("ru"));
    public static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("H:mm", new Locale("ru"));
    public static final String FILE_NAME = "waybill.xlsm";
    public static final String START_COMMAND = "/start";
    public static final String INFO_MESSAGE = "Введите сообщение с данными заказа";
    public static final String MILEAGE = "Введите показание одометра в ответ на это сообщение, или оставьте поле пустым и нажмите 'отпровить'";
    public static final String EMPTY_STRING = "";
    public static final String SPACE = " ";
    public final static String NUMBER = "Номер: ";
    public final static String DATE = "Дата: ";
    public final static String TIME = "Время: ";
    public final static String CUSTOMER = "Заказчик: ";
    public final static String VEHICLE_TYPE = "Тип ТС: ";
    public final static String WHITE_REG_MARK_REGEX = "([а-яА-Я]) ?([0-9]{3}) ?([а-яА-Я]{2}) ?([0-9]{2})([0-9]?)";
    public final static String YALOW_REG_MARK_REGEX = "([а-яА-Я]{2}) ?([0-9]{3}) ?([0-9]{2})";
    public final static String WHITE_REG_MARK_REPLACEMENT = "$1 $2 $3 $4$5";
    public final static String YALOW_REG_MARK_REPLACEMENT = "$1 $2 $3";
    public final static String REG_MARK_REGEX = WHITE_REG_MARK_REGEX + "|" + YALOW_REG_MARK_REGEX;
}
