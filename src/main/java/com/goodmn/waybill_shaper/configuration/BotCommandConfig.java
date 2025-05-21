package com.goodmn.waybill_shaper.configuration;

import com.goodmn.waybill_shaper.enums.BotCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class BotCommandConfig {
    private final Set<BotCommand> commands;

    @Bean
    public Map<String, BotCommand> botCommands() {
        return commands
                .stream()
                .collect(Collectors.toMap(c -> c.getClass().getSimpleName().toLowerCase(), c -> c));
    }
}
