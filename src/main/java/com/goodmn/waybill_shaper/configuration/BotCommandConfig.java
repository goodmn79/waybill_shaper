package com.goodmn.waybill_shaper.configuration;

import com.goodmn.waybill_shaper.command.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class BotCommandConfig {
    private final Set<Command> commands;

    @Bean
    public Map<String, Command> botCommands() {
        return commands
                .stream()
                .collect(Collectors.toMap(Command::cmd, c -> c));
    }
}
