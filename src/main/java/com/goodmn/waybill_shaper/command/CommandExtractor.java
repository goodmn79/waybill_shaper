package com.goodmn.waybill_shaper.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommandExtractor {
    private final Map<String, Command> commands;

    public Command extract(String text) {
        log.info("Извлечение команды");
        String stringCmd;
        if (text.startsWith("/")) {
            stringCmd = StringUtils.substringAfter(text, "/");
        } else {
            stringCmd = StringUtils.substringBefore(text, "_");
        }

        Command command = commands.get(stringCmd);
        log.info("Command was getting: '{}'", command != null);

        if (command != null) {
            return command;
        } else {
            return commands.get("error");
        }
    }
}
