package com.goodmn.waybill_shaper.keyboard;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MainKeyboard {
    private static final String waybill = "Сформировать пкутевой лист";

    private String date = "Дата";
    private String customer = "Заказчик";
    private String vehicle = "Транспортное средство";
    private String mileage = "Показания одометра";

    public InlineKeyboardMarkup mainKeyboard() {
        return new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{new InlineKeyboardButton(date).callbackData("date_" + date)},
                new InlineKeyboardButton[]{new InlineKeyboardButton(customer).callbackData("customer_")},
                new InlineKeyboardButton[]{new InlineKeyboardButton(vehicle).callbackData("vehicle_")},
                new InlineKeyboardButton[]{new InlineKeyboardButton(mileage).callbackData("mileage_")},
                new InlineKeyboardButton[]{new InlineKeyboardButton(waybill).callbackData("waybill_")}
        );
    }
}
