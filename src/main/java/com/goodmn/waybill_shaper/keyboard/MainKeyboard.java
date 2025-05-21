package com.goodmn.waybill_shaper.keyboard;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import lombok.Data;
import org.springframework.stereotype.Component;

import static com.goodmn.waybill_shaper.constant.ButtonName.*;
import static com.goodmn.waybill_shaper.constant.Cmd.*;

@Data
@Component
public class MainKeyboard {
    private String date = DATE;
    private String customer = CUSTOMER;
    private String vehicle = VEHICLE;
    private String mileage = MILEAGE;

    public InlineKeyboardMarkup mainKeyboard() {
        return new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{new InlineKeyboardButton(date).callbackData(SELECT_DATE_CMD + date)},
                new InlineKeyboardButton[]{new InlineKeyboardButton(customer).callbackData(CUSTOMER_CMD)},
                new InlineKeyboardButton[]{new InlineKeyboardButton(vehicle).callbackData(VEHICLE_CMD)},
                new InlineKeyboardButton[]{new InlineKeyboardButton(mileage).callbackData(MILEAGE_CMD)},
                new InlineKeyboardButton[]{new InlineKeyboardButton(GENERATE_WAYBILL).callbackData(GENERATE_WAYBILL_CMD)}
        );
    }
}
