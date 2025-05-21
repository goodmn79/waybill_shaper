package com.goodmn.waybill_shaper.configuration;

import com.goodmn.waybill_shaper.writer.Writeable;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WriteableConfig {
    private final List<Writeable> writers;

    @Bean
    public Writeable init() {
        for (int i = 1; i < writers.size(); i++) {
            writers.get(i - 1).setNext(writers.get(i));
        }
        return writers.get(0);
    }
}
