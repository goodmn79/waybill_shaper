package com.goodmn.waybill_shaper.command.impl;

import com.goodmn.waybill_shaper.cleaner.ChatCleaner;
import com.goodmn.waybill_shaper.command.Command;
import com.goodmn.waybill_shaper.executor.TelegramBotExecutor;
import com.goodmn.waybill_shaper.keyboard.MileageKeyboard;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.goodmn.waybill_shaper.constant.Cmd.INPUT_MILEAGE_CMD;
import static com.goodmn.waybill_shaper.constant.Constant.MILEAGE_INFO;

@Component(INPUT_MILEAGE_CMD)
@EqualsAndHashCode
@RequiredArgsConstructor
public class InputMileageCmd implements Command {
    private static final Logger log = LoggerFactory.getLogger(InputMileageCmd.class);
    private static int requestMileage = 0;

    private final TelegramBotExecutor executor;
    private final MileageKeyboard keyboard;
    private final ChatCleaner chatCleaner;

    @Override
    public String cmd() {
        return INPUT_MILEAGE_CMD;
    }

    @Override
    public void execute(Update update) {
        long chatId = update.callbackQuery().from().id();

        String text = update.callbackQuery().data();
        log.info("Input mileage is {}", text);

        setRequestMileage();
        SendResponse response = executor.sendMessage(new SendMessage(chatId, MILEAGE_INFO)
                .replyMarkup(keyboard.mileageKeyboard(text)));

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
