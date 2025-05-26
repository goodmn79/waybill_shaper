package com.goodmn.waybill_shaper.keyboard;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import org.springframework.stereotype.Component;

import static com.goodmn.waybill_shaper.constant.ButtonName.*;
import static com.goodmn.waybill_shaper.constant.Cmd.*;


@Component
public class MileageKeyboard {

    public InlineKeyboardMarkup mileageKeyboard(String data) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        if (data.equals(INPUT_MILEAGE_CMD + MILEAGE)) {
            button.setText(SKIP);
            button.setCallbackData(SKIP_CMD);
        } else {
            button.setText(RESET);
            button.setCallbackData(RESET_CMD);
        }

        return new InlineKeyboardMarkup()
                .addRow(button);

    }
}
