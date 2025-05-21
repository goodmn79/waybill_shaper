package com.goodmn.waybill_shaper.cleaner;

import com.goodmn.waybill_shaper.executor.TelegramBotExecutor;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.DeleteMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import javax.activation.PreDestroy;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatCleaner implements DisposableBean {

    private final MessageStorage messageStorage;
    private final TelegramBotExecutor executor;

    @Override
    public void destroy() {
        clearChat();
    }

    public void deleteLastMessage(long chatId) {
        if (messageStorage.containsMessages(chatId)) {
            log.info("Deleting last message from chat and storage");
            messageStorage.getMessages(chatId).forEach(messageId ->
                    executor.deleteLastMessage(new DeleteMessage(chatId, messageId))
            );

            messageStorage.clearStorage();
        }
    }

    public void deleteInputMessage(Update update) {
        long chatId;
        Integer messageId;

        if (update.message() == null) {
            return;
        }

        if (update.message().replyToMessage() != null) {
            log.info("Deleting input reply message");
            chatId = update.message().replyToMessage().chat().id();
            messageId = update.message().replyToMessage().messageId();
            executor.deleteLastMessage(new DeleteMessage(chatId, messageId));
        }

        log.info("Deleting input message");
        chatId = update.message().chat().id();
        messageId = update.message().messageId();
        executor.deleteLastMessage(new DeleteMessage(chatId, messageId));
    }

    public void saveSentMessage(SendResponse response) {
        messageStorage.add(response);
    }

    @PreDestroy
    public void clearChat() {
        Set<Long> chatIds = messageStorage.getChatIds();
        chatIds.forEach(l -> messageStorage.getMessages(l).forEach(m ->
                executor.deleteLastMessage(new DeleteMessage(l, m))
        ));
    }
}
