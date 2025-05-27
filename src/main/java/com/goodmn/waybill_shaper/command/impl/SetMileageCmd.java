package com.goodmn.waybill_shaper.command.impl;

import com.goodmn.waybill_shaper.cleaner.ChatCleaner;
import com.goodmn.waybill_shaper.command.Command;
import com.goodmn.waybill_shaper.executor.TelegramBotExecutor;
import com.goodmn.waybill_shaper.keyboard.MainKeyboard;
import com.goodmn.waybill_shaper.storage.DataStorage;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import static com.goodmn.waybill_shaper.constant.ButtonName.MILEAGE;
import static com.goodmn.waybill_shaper.constant.Cmd.SET_MILEAGE_CMD;
import static com.goodmn.waybill_shaper.constant.Constant.INFO;

@Slf4j
@Component(SET_MILEAGE_CMD)
@EqualsAndHashCode
@RequiredArgsConstructor
public class SetMileageCmd implements Command {
    private final DataStorage storage;
    private final ChatCleaner chatCleaner;
    private final MainKeyboard keyboard;
    private final TelegramBotExecutor executor;

    @Override
    public String cmd() {
        return SET_MILEAGE_CMD;
    }

    @Override
    public void execute(Update update) {
        long chatId = update.message().chat().id();

        chatCleaner.deleteLastMessage(chatId);
        chatCleaner.deleteInputMessage(update);

        String mileage = update.message().text();

        if (StringUtils.isNumeric(mileage)) {
            storage.addData(MILEAGE, mileage);
            keyboard.setMileage(mileage);
            log.info("Data has been saved");
        }
        SendResponse response = executor.sendMessage(new SendMessage(chatId, INFO)
                .replyMarkup(keyboard.mainKeyboard()));
        chatCleaner.saveSentMessage(response);
    }
}
