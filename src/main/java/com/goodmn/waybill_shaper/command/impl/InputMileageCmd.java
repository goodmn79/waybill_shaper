package com.goodmn.waybill_shaper.command.impl;

import com.goodmn.waybill_shaper.cleaner.ChatCleaner;
import com.goodmn.waybill_shaper.command.Command;
import com.goodmn.waybill_shaper.executor.TelegramBotExecutor;
import com.goodmn.waybill_shaper.keyboard.ResetKeyboard;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.goodmn.waybill_shaper.constant.Cmd.INPUT_MILEAGE_CMD;

@Component(INPUT_MILEAGE_CMD)
@EqualsAndHashCode
@RequiredArgsConstructor
public class InputMileageCmd implements Command {
    private static int requestMileage = 0;

    private final TelegramBotExecutor executor;
    private final ResetKeyboard keyboard;
    private final ChatCleaner chatCleaner;

    @Override
    public String cmd() {
        return INPUT_MILEAGE_CMD;
    }

    @Override
    public void execute(Update update) {
        long chatId = update.callbackQuery().from().id();

        setRequestMileage();
        SendResponse response = executor.sendMessage(new SendMessage(chatId, "Введите показания одометра:")
                .replyMarkup(keyboard.resetKeyboard()));

        chatCleaner.deleteLastMessage(chatId);

        chatCleaner.saveSentMessage(response);
    }

    public static boolean isRequestMileage() {
        return requestMileage == 1;
    }

    public static void setRequestMileage() {
        requestMileage = 1;
    }

    public static void removeRequestMileage() {
        requestMileage = 0;
    }
}
