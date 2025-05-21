package com.goodmn.waybill_shaper.cleaner;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageStorage {
    private final Map<Long, List<Integer>> messageStorage = new HashMap<>();

    public void add(SendResponse response) {
        log.info("Saving message to storage");
        Message message = response.message();
        Long chatId = message.chat().id();
        List<Integer> messageIds;
        if (messageStorage.get(chatId) != null) {
            messageIds = messageStorage.get(chatId);
        } else {
            messageIds = new ArrayList<>();
        }
        Integer messageId;

        if (message.replyToMessage() != null) {
            messageId = message.replyToMessage().messageId();
        } else {
            messageId = message.messageId();
        }
        messageIds.add(messageId);
        messageStorage.put(chatId, messageIds);
    }

    public boolean containsMessages(Long chatId) {
        return messageStorage.containsKey(chatId);
    }

    public Set<Long> getChatIds() {
        return messageStorage.keySet();
    }

    public List<Integer> getMessages(Long chatId) {
        return messageStorage.get(chatId);
    }

    public void clearStorage() {
        messageStorage.clear();
    }

}
