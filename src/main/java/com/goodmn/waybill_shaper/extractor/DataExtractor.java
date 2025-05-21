package com.goodmn.waybill_shaper.extractor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataExtractor implements Extractor<String> {
    @Override
    public String extract(String messageText) {
        return StringUtils.substringAfter(messageText, "|");
    }
}
