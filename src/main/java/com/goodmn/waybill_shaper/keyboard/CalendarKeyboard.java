package com.goodmn.waybill_shaper.keyboard;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.YearMonth;

import static com.goodmn.waybill_shaper.constant.Cmd.*;
import static com.goodmn.waybill_shaper.dto.DateData.NUMERIC_DATE_FORMATTER;

@Setter
@Component
public class CalendarKeyboard {
    private static final LocalDate TODAY = LocalDate.now();
    private YearMonth yearMonth = YearMonth.from(TODAY);

    public InlineKeyboardMarkup calendar() {
        final InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();

        LocalDate firstDay = yearMonth.atDay(1);
        int daysInMonth = yearMonth.lengthOfMonth();
        int dayOfWeek = (firstDay.getDayOfWeek().getValue() % 7) - 1;

        int year = yearMonth.getYear();
        String month = month(yearMonth.getMonthValue());
        String monthYear = month + " " + year;

        keyboard.addRow(new InlineKeyboardButton(monthYear).callbackData(IGNORE_CMD));

        String[] weekDays = {"Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс"};
        InlineKeyboardButton[] weekDayButtons = new InlineKeyboardButton[7];
        for (int i = 0; i < 7; i++) {
            weekDayButtons[i] = new InlineKeyboardButton(weekDays[i]).callbackData(IGNORE_CMD);
        }
        keyboard.addRow(weekDayButtons);

        int day = 1;
        for (int i = 0; i < 6; i++) {
            if (day > daysInMonth) break;
            InlineKeyboardButton[] row = new InlineKeyboardButton[7];
            for (int j = 0; j < 7; j++) {
                if (i == 0 && j < dayOfWeek || day > daysInMonth) {
                    row[j] = new InlineKeyboardButton(" ").callbackData(IGNORE_CMD);
                } else {
                    LocalDate selectedDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), day);
                    String date = selectedDate.format(NUMERIC_DATE_FORMATTER);
                    String buttonText = String.valueOf(day);
                    if (selectedDate.equals(TODAY)) {
                        buttonText = "\uD83D\uDFE2";
                    }
                    String callbackData = selectedDate.isBefore(TODAY) ? IGNORE_CMD : SET_DATE_CMD + date;
                    row[j] = new InlineKeyboardButton(buttonText).callbackData(callbackData);
                    day++;
                }
            }
            keyboard.addRow(row);
        }

        YearMonth prevMonth = yearMonth.minusMonths(1);
        YearMonth nextMonth = yearMonth.plusMonths(1);
        keyboard.addRow(new InlineKeyboardButton("◀️").
                        callbackData(NAV_CMD + prevMonth.getYear() + " " + prevMonth.getMonthValue()),
                new InlineKeyboardButton("▶️").
                        callbackData(NAV_CMD + nextMonth.getYear() + " " + nextMonth.getMonthValue())
        );
        return keyboard;
    }

    private String month(int month) {
        return switch (month) {
            case 1 -> "Январь";
            case 2 -> "Февраль";
            case 3 -> "Март";
            case 4 -> "Апрель";
            case 5 -> "Май";
            case 6 -> "Июнь";
            case 7 -> "Июль";
            case 8 -> "Август";
            case 9 -> "Сентябрь";
            case 10 -> "Октябрь";
            case 11 -> "Ноябрь";
            case 12 -> "Декабрь";
            default -> throw new IllegalArgumentException("Invalid month value: " + month);
        };
    }
}
