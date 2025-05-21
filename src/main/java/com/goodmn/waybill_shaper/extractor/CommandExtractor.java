package com.goodmn.waybill_shaper.extractor;

import com.goodmn.waybill_shaper.command.Command;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommandExtractor implements Extractor<Command> {
    private final Map<String, Command> commands;

    @Override
    public Command extract(String text) {
        log.info("CommandS: {}", commands);

        log.warn("Extracting command");
        String stringCmd;
        if (text.startsWith("/")) {
            stringCmd = StringUtils.substringAfter(text, "/");
        } else {
            stringCmd = StringUtils.substringBefore(text, " ");
        }

        log.warn("Extracting command: {}", stringCmd);

        Command command = commands.get(stringCmd);

        if (command != null) {
            log.info("Command successfully extracted");
            return command;
        } else {
            log.error("Command not found");
            return commands.get("error");
        }
    }
}
