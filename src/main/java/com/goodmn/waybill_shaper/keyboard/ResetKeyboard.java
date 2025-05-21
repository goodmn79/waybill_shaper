package com.goodmn.waybill_shaper.keyboard;

import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import org.springframework.stereotype.Component;

import static com.goodmn.waybill_shaper.constant.ButtonName.RESET;

@Component
public class ResetKeyboard {

    public ReplyKeyboardMarkup resetKeyboard() {
        return new ReplyKeyboardMarkup(new KeyboardButton(RESET))
                .oneTimeKeyboard(true)
                .resizeKeyboard(true);
    }
}
